package com.example.service_subway.core;

import com.example.service_subway.factory.SubwayFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.service_subway.factory.LocationFactory.location;
import static com.example.service_subway.factory.SubwayFactory.subways;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NearestSubwayStationServiceTest {

    private NearestSubwayStationService service;

    @BeforeEach
    void setUp() {
        service = new NearestSubwayStationService(new DistanceCalculatorService());
    }

    @Test
    void getNearest_ShouldReturnSubway() {
        var givenSubways = subways();
        var givenUserLocation = location();
        int givenSearchRadius = 1000;
        var actualSubway = service.get(givenSubways, givenUserLocation, givenSearchRadius).orElseThrow();

        var expectedSubway = SubwayFactory.nyamiga();
        assertEquals(expectedSubway, actualSubway);
    }

    @Test
    void getNearest_notSubwayInSearchRadius_shouldReturnEmpty() {
        var givenSubways = subways();
        var givenUserLocation = location();
        int givenSearchRadius = 5;
        var actualSubway = service.get(givenSubways, givenUserLocation, givenSearchRadius);
        assertTrue(actualSubway.isEmpty());
    }
}
