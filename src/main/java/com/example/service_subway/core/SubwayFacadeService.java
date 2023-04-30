package com.example.service_subway.core;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.plugins.RegionService;
import com.example.service_subway.core.plugins.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayFacadeService {
    private final RegionService regionService;
    private final SubwayService subwayService;

    public List<Subway> list(String cityName) {
        Region region = regionService.getByName(cityName).orElseThrow();
        return subwayService.listByCity(region);
    }
}
