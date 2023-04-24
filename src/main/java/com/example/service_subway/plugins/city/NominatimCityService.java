package com.example.service_subway.plugins.city;

import com.example.service_subway.core.model.City;
import com.example.service_subway.core.plugins.CityService;
import com.example.service_subway.plugins.city.mapper.NominatimCityResponseMapper;
import com.example.service_subway.plugins.city.model.NominatimCityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class NominatimCityService implements CityService {

    private final RestTemplate restTemplate;
    private final NominatimCityResponseMapper mapper;

    private final String BASE_URL;

    public NominatimCityService(RestTemplate restTemplate,
                                NominatimCityResponseMapper mapper,
                                @Value("${app.plugin.city.nominatim.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        BASE_URL = baseUrl;
    }

    @Override
    public Optional<City> getByName(String name) {
        if (isEmpty(name)) {
            return Optional.empty();
        }
        URI uri = buildUri(name);
        var response = restTemplate.getForObject(uri, NominatimCityResponse[].class);
        return buildCity(response);
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

    private Optional<City> buildCity(NominatimCityResponse[] response) {
        return Arrays.stream(response)
                .findFirst()
                .map(mapper::map);
    }
}
