package com.example.service_subway.core.api;

import com.example.service_subway.core.ApplicationFacadeService;
import com.example.service_subway.core.model.SubwayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/metro")
public class NearestSubwayController {

    private final ApplicationFacadeService service;

    @GetMapping
    public ResponseEntity<SubwayResponse> getStationsByCity(@RequestParam float latitude,
                                                            @RequestParam float longitude,
                                                            @RequestParam int radius) {
        var response = service.getNearestSubwayStation(latitude, longitude, radius);
        return ResponseEntity.ok(response);
    }
}
