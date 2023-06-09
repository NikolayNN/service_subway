package com.example.service_subway.plugins.subway;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.plugins.SubwayService;
import com.example.service_subway.plugins.subway.mapper.OverpassSubwayResponseMapper;
import com.example.service_subway.plugins.subway.model.OverpassSubwayResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class SubwayServiceOverpass implements SubwayService {

    private final RestTemplate restTemplate;
    private final OverpassSubwayResponseMapper mapper;

    private final String BASE_URL;

    public SubwayServiceOverpass(RestTemplate restTemplate,
                                 OverpassSubwayResponseMapper mapper,
                                 @Value("${app.plugin.subway.overpass.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        BASE_URL = baseUrl;
    }

    @Override
    public List<Subway> listByCity(Region region) {
        URI uri = buildUri(region);
        try {
            var response = restTemplate.getForObject(uri, OverpassSubwayResponse.class);
            return mapper.map(response);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private URI buildUri(Region region) {
        String query = String.format("data=[out:json];node[station=subway](%s);out;", toString(region));
        return URI.create(BASE_URL + "?" + query);
    }

    private String toString(Region region) {
        return toString(region.bounds().southWest()) + "," + toString(region.bounds().northEast());
    }

    private String toString(Coordinate coordinate) {
        return coordinate.latitude() + "," + coordinate.longitude();
    }
}

