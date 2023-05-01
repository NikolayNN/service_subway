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
@RequestMapping("/metro")
public class NearestSubwayController {

    private final ApplicationFacadeService service;

    @GetMapping
    public ResponseEntity<SubwayResponse> getStationsByCity(@RequestParam float latitude, @RequestParam float longitude) {
        var response = service.getNearestSubwayStation(latitude, longitude);
        return ResponseEntity.ok(response);
    }
}