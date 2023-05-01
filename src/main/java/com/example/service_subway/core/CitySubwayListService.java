package com.example.service_subway.core;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.plugins.RegionService;
import com.example.service_subway.core.plugins.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitySubwayListService {
    private final RegionService regionService;
    private final SubwayService subwayService;

    @Cacheable(value = "subways")
    public List<Subway> list(String cityName) {
        return regionService.getByName(cityName)
                .map(subwayService::listByCity)
                .orElse(List.of());
    }
}
