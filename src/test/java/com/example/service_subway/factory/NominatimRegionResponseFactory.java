package com.example.service_subway.factory;

import com.example.service_subway.plugins.region.model.NominatimRegionResponse;

import static com.example.service_subway.factory.BoundingBoxArrayFactory.boundingBoxArray;
import static com.example.service_subway.factory.RegionNameFactory.regionName;

public class NominatimRegionResponseFactory {
    public static NominatimRegionResponse nominatimRegionResponse() {
        return new NominatimRegionResponse(regionName(), boundingBoxArray());
    }

    public static NominatimRegionResponse[] nominatimRegionResponseArray() {
        return new NominatimRegionResponse[]{
                nominatimRegionResponse()
        };
    }
}
