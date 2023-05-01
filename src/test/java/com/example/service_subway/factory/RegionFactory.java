package com.example.service_subway.factory;

import com.example.service_subway.core.model.Region;

import static com.example.service_subway.factory.BoundingBoxFactory.boundingBox;
import static com.example.service_subway.factory.RegionNameFactory.regionName;

public class RegionFactory {
    public static Region region() {
        return new Region(
                regionName(),
                boundingBox());
    }
}
