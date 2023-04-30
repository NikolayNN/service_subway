package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.Region;

import java.util.Optional;

public interface RegionService {
    Optional<Region> getByName(String name);
}
