package com.example.service_subway.factory;

import com.example.service_subway.core.model.Coordinate;

public class CoordinateFactory {
    public static Coordinate coordinate() {
        return new Coordinate(53.90F, 27.53F);
    }
}
