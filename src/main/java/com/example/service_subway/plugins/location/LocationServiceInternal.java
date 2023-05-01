package com.example.service_subway.plugins.location;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Location;
import com.example.service_subway.core.plugins.LocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class LocationServiceInternal implements LocationService {
    private final RestTemplate restTemplate;
    private final String BASE_URL;

    public LocationServiceInternal(RestTemplate restTemplate,
                                   @Value("${app.plugin.location.internal.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        BASE_URL = baseUrl;
    }

    @Override
    public Optional<Location> get(Coordinate coordinate) {
        if (coordinate == null) {
            return Optional.empty();
        }
        URI uri = buildUri(coordinate);
        try {
            var response = restTemplate.getForObject(uri, LocationResponse.class);
            return buildLocation(response, coordinate);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private URI buildUri(Coordinate coordinate) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("lat", coordinate.latitude())
                .queryParam("lon", coordinate.longitude())
                .build()
                .toUri();
    }

    private Optional<Location> buildLocation(LocationResponse response, Coordinate coordinate) {
        return Optional.ofNullable(response)
                .map(r -> new Location(coordinate, r.country(), r.city()));
    }

    private record LocationResponse(String country, String city) {
    }
}
