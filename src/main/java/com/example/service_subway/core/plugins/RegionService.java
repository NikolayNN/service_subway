package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.Region;

import java.util.Optional;

/**
 * Provides a service for retrieving {@link Region}.
 */
public interface RegionService {

    /**
     * Retrieves a region by name.
     *
     * @param name the name of the region to retrieve, it can be city name, country name, region name, state name and etc
     * @return an {@code Optional} containing the region, or an empty {@code Optional} if the region could not be found
     */
    Optional<Region> getByName(String name);
}
