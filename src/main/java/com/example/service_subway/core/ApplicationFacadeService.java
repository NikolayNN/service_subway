package com.example.service_subway.core;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.SubwayResponse;
import com.example.service_subway.core.plugins.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationFacadeService {
    private final LocationService locationService;
    private final CitySubwayListService subwayListService;
    private final NearestSubwayStationService nearestSubwayStationService;

    public SubwayResponse getNearestSubwayStation(float latitude, float longitude, int radius) {
        var coordinate = new Coordinate(latitude, longitude);
        var location = locationService.get(coordinate).orElseThrow();
        var subways = subwayListService.list(location.city());
        var subway = nearestSubwayStationService.get(subways, location, radius).orElseThrow();
        return SubwayResponse.of(location, subway);
    }
}
