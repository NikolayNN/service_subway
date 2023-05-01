package com.example.service_subway.plugins.region;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.plugins.RegionService;
import com.example.service_subway.plugins.region.mapper.NominatimRegionResponseMapper;
import com.example.service_subway.plugins.region.model.NominatimRegionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class RegionServiceNominatim implements RegionService {

    private final RestTemplate restTemplate;
    private final NominatimRegionResponseMapper mapper;

    private final String BASE_URL;

    public RegionServiceNominatim(RestTemplate restTemplate,
                                  NominatimRegionResponseMapper mapper,
                                  @Value("${app.plugin.city.nominatim.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        BASE_URL = baseUrl;
    }

    @Override
    public Optional<Region> getByName(String name) {
        if (isEmpty(name)) {
            return Optional.empty();
        }
        try {
            URI uri = buildUri(name);
            var response = restTemplate.getForObject(uri, NominatimRegionResponse[].class);
            return buildRegion(response);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
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
                .map(mapper::map);
    }
}
