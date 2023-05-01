package com.example.service_subway.factory;

import com.example.service_subway.core.model.Location;

import static com.example.service_subway.factory.CoordinateFactory.coordinate;
import static com.example.service_subway.factory.LocationResponseFactory.locationResponse;

public class LocationFactory {
    public static Location location() {
        var coordinate = coordinate();
        var locationResponse = locationResponse();
        return new Location(coordinate, locationResponse.country(), locationResponse.city());
    }
}
