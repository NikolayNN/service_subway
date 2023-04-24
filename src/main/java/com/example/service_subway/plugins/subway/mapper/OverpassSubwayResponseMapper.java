package com.example.service_subway.plugins.subway.mapper;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.plugins.subway.model.OverpassSubwayResponse;
import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OverpassSubwayResponseMapper implements Mapper<OverpassSubwayResponse, List<Subway>> {
    @Override
    public List<Subway> map(OverpassSubwayResponse response) {
        if (isEmpty(response)) {
            return List.of();
        }
        return Arrays.stream(response.elements())
                .map(this::map)
                .toList();
    }

    private boolean isEmpty(OverpassSubwayResponse response) {
        return response == null || response.elements() == null;
    }

    private Subway map(OverpassSubwayResponse.Element element) {
        return new Subway(element.tags().name(), element.tags().colour(), mapCoordinate(element));
    }

    private Coordinate mapCoordinate(OverpassSubwayResponse.Element element) {
        return new Coordinate(element.lat(), element.lon());
    }
}
