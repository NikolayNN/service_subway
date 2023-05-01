package com.example.service_subway.core;

import com.example.service_subway.core.model.Location;
import com.example.service_subway.core.model.Subway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NearestSubwayStationService {

    private final int searchRadiusMeters;
    private final DistanceCalculator distanceCalculator;

    public NearestSubwayStationService(DistanceCalculator distanceCalculator,
                                       @Value("${app.search-radius-meters}") int searchRadiusMeters) {
        this.distanceCalculator = distanceCalculator;
        this.searchRadiusMeters = searchRadiusMeters;
    }

    public Optional<Subway> get(List<Subway> subways, Location location) {
        return subways.stream()
                .map(subway -> new SimpleEntry<>(subway, distanceCalculator.calculate(subway.coordinate(), location.coordinate())))
                .filter(entry -> entry.getValue() <= searchRadiusMeters)
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
