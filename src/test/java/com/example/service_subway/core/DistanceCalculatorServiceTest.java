package com.example.service_subway.core;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.factory.CoordinateFactory;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static com.example.service_subway.factory.CoordinateFactory.coordinate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceCalculatorServiceTest {

    private final static double DELTA = 1e-6;
    private final DistanceCalculatorService service = new DistanceCalculatorService();

    @Test
    public void testCalculate_theSamePoint_shouldReturnZero() {
        var pointA = coordinate();
        var pointB = coordinate();
        var distance = service.calculate(pointA, pointB);
        assertEquals(0.0, distance, DELTA);
    }

    @Test
    public void testCalculate_differentCoordinates_shouldReturnDistance() {
        var pointA = new Coordinate(53.905271F, 27.553513F);
        var pointB = new Coordinate(53.906122F, 27.563954F);
        var distance = service.calculate(pointA, pointB);
        assertEquals(690.45433447446, distance, DELTA);
    }
}
