package com.example.service_subway.core;

import com.example.service_subway.core.plugins.RegionService;
import com.example.service_subway.core.plugins.SubwayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.service_subway.factory.RegionFactory.region;
import static com.example.service_subway.factory.RegionNameFactory.regionName;
import static com.example.service_subway.factory.SubwayFactory.subways;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitySubwayListServiceTest {

    @Mock
    private RegionService regionService;
    @Mock
    private SubwayService subwayService;
    private CitySubwayListService service;

    @BeforeEach
    void setUp() {
        service = new CitySubwayListService(regionService, subwayService);
    }

    @Test
    void getSubwaysList_shouldReturnSubways() {
        var givenCityName = regionName();
        var givenRegion = region();
        var givenSubways = subways();
        when(regionService.getByName(givenCityName)).thenReturn(Optional.of(givenRegion));
        when(subwayService.listByCity(givenRegion)).thenReturn(givenSubways);

        var actualSubways = service.list(givenCityName);

        var expectedSubways = subways();
        assertEquals(expectedSubways, actualSubways);
    }

    @Test
    void getSubwaysList_regionEmpty_shouldReturnEmptyList() {
        var givenCityName = regionName();
        when(regionService.getByName(givenCityName)).thenReturn(Optional.empty());

        var actualSubways = service.list(givenCityName);

        assertEquals(List.of(), actualSubways);
    }
}
