package com.example.service_subway.core.api;

import com.example.service_subway.core.ApplicationFacadeService;
import com.example.service_subway.core.model.SubwayResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(description = "get nearest subway station by coordinates in search radius")
    public ResponseEntity<SubwayResponse> getNearest(@RequestParam float latitude,
                                                     @RequestParam float longitude,
                                                     @Parameter(description = "search radius in meters")
                                                     @RequestParam(required = false, defaultValue = "1000") int radius) {
        var response = service.getNearestSubwayStation(latitude, longitude, radius);
        return ResponseEntity.ok(response);
    }
}
