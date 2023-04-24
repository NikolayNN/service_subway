package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.City;

import java.util.Optional;

public interface CityService {
    Optional<City> getByName(String name);
}
