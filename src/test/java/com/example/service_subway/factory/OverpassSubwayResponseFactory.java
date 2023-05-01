package com.example.service_subway.factory;

import com.example.service_subway.core.model.Subway;
import com.example.service_subway.plugins.subway.model.OverpassSubwayResponse;

import java.util.List;

import static com.example.service_subway.factory.SubwayFactory.*;

public class OverpassSubwayResponseFactory {

    public static OverpassSubwayResponse overpassSubwayResponse() {
        List<Subway> subways = subways();
        OverpassSubwayResponse.Element[] elements = {
                element(subways.get(0)),
                element(subways.get(1))
        };
        return new OverpassSubwayResponse(elements);
    }

    private static OverpassSubwayResponse.Element element(Subway subway) {
        return new OverpassSubwayResponse.Element(
                subway.coordinate().latitude(),
                subway.coordinate().longitude(),
                new OverpassSubwayResponse.Tags(subway.color(), subway.name()));
    }
}
