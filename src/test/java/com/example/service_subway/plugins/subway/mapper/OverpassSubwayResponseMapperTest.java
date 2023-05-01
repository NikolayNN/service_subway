package com.example.service_subway.plugins.subway.mapper;

import com.example.service_subway.plugins.subway.model.OverpassSubwayResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.service_subway.factory.OverpassSubwayResponseFactory.overpassSubwayResponse;
import static com.example.service_subway.factory.SubwayFactory.subways;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OverpassSubwayResponseMapperTest {

    private final OverpassSubwayResponseMapper mapper = new OverpassSubwayResponseMapper();

    @Test
    public void testEmptyResponse() {
        var givenOverpassSubwayResponse = new OverpassSubwayResponse(new OverpassSubwayResponse.Element[]{});
        var actualSubways = mapper.map(givenOverpassSubwayResponse);
        assertEquals(List.of(), actualSubways);
    }

    @Test
    public void testMapping() {
        var givenOverpassSubwayResponse = overpassSubwayResponse();

        var actualSubways = mapper.map(givenOverpassSubwayResponse);

        var expectedSubways = subways();
        assertEquals(expectedSubways, actualSubways);
    }

}
