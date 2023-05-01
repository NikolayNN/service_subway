package com.example.service_subway.plugins.region;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.plugins.RegionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class NominatimRegionService implements RegionService {

    private final RestTemplate restTemplate;

    private final String BASE_URL;

    public NominatimRegionService(RestTemplate restTemplate,
                                  @Value("${app.plugin.region.nominatim.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        BASE_URL = baseUrl;
    }

    @Override
    public Optional<Region> getByName(String name) {
        if (isEmpty(name)) {
            return Optional.empty();
        }
        URI uri = buildUri(name);
        var response = restTemplate.getForObject(uri, NominatimRegionResponse[].class);
        return buildRegion(response);
    }

    private URI buildUri(String name) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("q", name)
                .queryParam("format", "json")
                .queryParam("polygon_geojson", 1)
                .queryParam("addressdetails", 1)
                .queryParam("limit", 1)
                .build()
                .toUri();
    }

    private Optional<Region> buildRegion(NominatimRegionResponse[] response) {
        return Arrays.stream(response)
                .findFirst()
                .map(this::mapRegion);
    }

    private Region mapRegion(NominatimRegionResponse response) {
        return new Region(
                response.displayName(),
                this.mapBoundingBox(response.boundingbox()));
    }

    public Region.BoundingBox mapBoundingBox(float[] bbox) {
        return new Region.BoundingBox(new Coordinate(bbox[0], bbox[2]), new Coordinate(bbox[1], bbox[3]));
    }

    private record NominatimRegionResponse(
            @JsonProperty("display_name") String displayName,
            float[] boundingbox) {
    }
}
