package com.example.service_subway.core;

import com.example.service_subway.core.model.Location;
import com.example.service_subway.core.model.Subway;
import org.springframework.stereotype.Service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NearestSubwayStationService {
    private final DistanceCalculatorService distanceCalculatorService;

    public NearestSubwayStationService(DistanceCalculatorService distanceCalculatorService) {
        this.distanceCalculatorService = distanceCalculatorService;
    }

    public Optional<Subway> get(List<Subway> subways, Location location, int searchRadiusMeters) {
        return subways.stream()
                .map(subway -> new SimpleEntry<>(subway, distanceCalculatorService.calculate(subway.coordinate(), location.coordinate())))
                .filter(entry -> entry.getValue() <= searchRadiusMeters)
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
