package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Location;

import java.util.Optional;

public interface LocationService {
    Optional<Location> get(Coordinate coordinate);
}
