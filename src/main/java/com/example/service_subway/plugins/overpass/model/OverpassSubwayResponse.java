package com.example.service_subway.plugins.overpass.model;


public record OverpassSubwayResponse(Element[] elements) {

    public record Element(float lat, float lon, Tags tags) {
    }

    public record Tags(String colour, String name) {
    }
}
