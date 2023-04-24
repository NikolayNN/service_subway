package com.example.service_subway.plugins.subway.model;


public record OverpassSubwayResponse(Element[] elements) {

    public record Element(float lat, float lon, Tags tags) {
    }

    public record Tags(String colour, String name) {
    }
}
