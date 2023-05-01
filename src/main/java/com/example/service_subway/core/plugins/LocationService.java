package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Location;

import java.util.Optional;

/**
 * Provides a service for retrieving the location information for a given coordinates.
 */
public interface LocationService {

    /**
     * Retrieves the location information for a given coordinates.
     *
     * @param coordinate the coordinates to get the location for
     * @return an {@code Optional} containing the location information, or an empty {@code Optional} if the location
     * could not be retrieved
     */
    Optional<Location> get(Coordinate coordinate);
}
