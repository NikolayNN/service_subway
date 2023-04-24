package com.example.service_subway.core;

import com.example.service_subway.core.model.City;
import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.plugins.CityService;
import com.example.service_subway.core.plugins.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayFacadeService {
    private final CityService cityService;
    private final SubwayService subwayService;

    public List<Subway> list(String cityName) {
        City city = cityService.getByName(cityName).orElseThrow();
        return subwayService.listByCity(city);
    }
}
