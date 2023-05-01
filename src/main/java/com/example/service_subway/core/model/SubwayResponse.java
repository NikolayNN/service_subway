package com.example.service_subway.core.model;

public record SubwayResponse(String country, String city, String metro, String color, Coordinate metroCoordinate) {
    public static SubwayResponse of(Location location, Subway subway) {
        return new SubwayResponse(location.country(), location.city(), subway.name(), subway.color(), subway.coordinate());
    }
}
