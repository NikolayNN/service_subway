package com.example.service_subway.factory;

import static com.example.service_subway.plugins.location.LocationServiceInternal.LocationResponse;

public class LocationResponseFactory {
    public static LocationResponse locationResponse() {
        return new LocationResponse("Беларусь", RegionNameFactory.regionName());
    }
}
