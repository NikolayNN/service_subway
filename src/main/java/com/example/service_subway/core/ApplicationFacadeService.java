package com.example.service_subway.core;

import com.example.service_subway.core.exception.LocationNotFoundException;
import com.example.service_subway.core.exception.NearestSubwayNotFoundException;
import com.example.service_subway.core.exception.SubwayNotFoundException;
import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.SubwayResponse;
import com.example.service_subway.core.plugins.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Provides a facade for retrieving the nearest subway station based on a given location
 */
@Service
@RequiredArgsConstructor
public class ApplicationFacadeService {
    private final LocationService locationService;
    private final CitySubwayListService subwayListService;
    private final NearestSubwayStationService nearestSubwayStationService;

    public SubwayResponse getNearestSubwayStation(float latitude, float longitude, int radius) {
        var coordinate = new Coordinate(latitude, longitude);
        var location = locationService.get(coordinate)
                .orElseThrow(() -> new LocationNotFoundException(coordinate));
        var subways = subwayListService.list(location.city());
        if (CollectionUtils.isEmpty(subways)) {
            throw new SubwayNotFoundException(location);
        }
        var subway = nearestSubwayStationService.get(subways, location, radius)
                .orElseThrow(() -> new NearestSubwayNotFoundException(location, radius));
        return SubwayResponse.of(location, subway);
    }
}
