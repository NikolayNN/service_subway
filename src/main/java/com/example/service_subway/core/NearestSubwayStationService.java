package com.example.service_subway.core;

import com.example.service_subway.core.model.Location;
import com.example.service_subway.core.model.Subway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NearestSubwayStationService {
    private final DistanceCalculatorService distanceCalculatorService;

    public NearestSubwayStationService(DistanceCalculatorService distanceCalculatorService) {
        this.distanceCalculatorService = distanceCalculatorService;
    }

    public Optional<Subway> get(List<Subway> subways, Location location, int searchRadiusMeters) {
        TempSubway nearest = findNearestSubway(subways, location);
        if (nearest.subway().isPresent() && nearest.distance <= searchRadiusMeters) {
            return nearest.subway();
        } else {
            return Optional.empty();
        }
    }

    private TempSubway findNearestSubway(List<Subway> subways, Location location) {
        TempSubway nearest = TempSubway.empty();
        for (Subway subway : subways) {
            double distance = distanceCalculatorService.calculate(subway.coordinate(), location.coordinate());
            if (distance < nearest.distance) {
                nearest = new TempSubway(subway, distance);
            }
        }
        return nearest;
    }


    /**
     * The TempSubway class represents a model that includes a subway station and the distance from that station
     * to the user's current position.
     */
    @AllArgsConstructor
    private static class TempSubway {
        private Subway subway;

        /**
         * Distance in meters
         */
        private double distance;

        public Optional<Subway> subway() {
            return Optional.ofNullable(subway);
        }

        static TempSubway empty() {
            return new TempSubway(null, Double.MAX_VALUE);
        }
    }
}
