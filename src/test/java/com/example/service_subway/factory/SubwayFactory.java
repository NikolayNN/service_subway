package com.example.service_subway.factory;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Subway;

import java.util.List;

public class SubwayFactory {
    public static Subway nyamiga() {
        return new Subway("Nyamiga", "blue", new Coordinate(53.905938F, 27.554049F));
    }

    public static Subway kypalayskaya() {
        return new Subway("Kypalayskaya", "blue", new Coordinate(53.900889F, 27.561742F));
    }

    public static List<Subway> subways() {
        return List.of(nyamiga(), kypalayskaya());
    }
}
