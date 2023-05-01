package com.example.service_subway.core.api;

import com.example.service_subway.core.model.Subway;
import com.example.service_subway.core.CitySubwayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class SubwayController {

    private final CitySubwayListService service;

    @GetMapping("/{city}")
    public List<Subway> getStationsByCity(@PathVariable String city) {
        return service.list(city);
    }
}
