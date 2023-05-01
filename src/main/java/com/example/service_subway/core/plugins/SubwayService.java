package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Subway;

import java.util.List;

/**
 * Provides a service for retrieving {@link Subway} stations in a given {@link Region}.
 */
public interface SubwayService {

    /**
     * Retrieves a list of subway stations in the given region.
     *
     * @param region the region to retrieve subway stations for
     * @return a list of {@code Subway} objects representing the subway stations in the given region
     */
    List<Subway> listByCity(Region region);
}
