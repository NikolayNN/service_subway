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
public class OverpassSubwayService implements SubwayService {

    private final RestTemplate restTemplate;
    private final OverpassSubwayResponseMapper mapper;

    private final String BASE_URL;

    public OverpassSubwayService(RestTemplate restTemplate,
                                 OverpassSubwayResponseMapper mapper,
                                 @Value("${app.plugin.subway.overpass.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        BASE_URL = baseUrl;
    }

    @Override
    public List<Subway> listByCity(Region region) {
        URI uri = buildUri(region);
        var response = restTemplate.getForObject(uri, OverpassSubwayResponse.class);
        return mapper.map(response);
    }

    private URI buildUri(Region region) {
        String query = String.format("[out:json];node[station=subway](%s);out;", boundBoxString(region));
        String uri = BASE_URL + "?data=" + query;
        return URI.create(uri);
    }

    private String boundBoxString(Region region) {
        return toString(region.bounds().southWest()) + "," + toString(region.bounds().northEast());
    }

    private String toString(Coordinate coordinate) {
        return coordinate.latitude() + "," + coordinate.longitude();
    }
}

