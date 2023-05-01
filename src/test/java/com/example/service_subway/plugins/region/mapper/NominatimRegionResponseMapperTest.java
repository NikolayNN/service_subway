package com.example.service_subway.plugins.region.mapper;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Region.BoundingBox;
import com.example.service_subway.factory.NominatimRegionResponseFactory;
import com.example.service_subway.plugins.region.mapper.NominatimRegionResponseMapper.NominatimBoundingBoxMapper;
import com.example.service_subway.plugins.region.model.NominatimRegionResponse;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.service_subway.factory.BoundingBoxArrayFactory.boundingBoxArray;
import static com.example.service_subway.factory.BoundingBoxFactory.boundingBox;
import static com.example.service_subway.factory.NominatimRegionResponseFactory.nominatimRegionResponse;
import static com.example.service_subway.factory.RegionFactory.region;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NominatimRegionResponseMapperTest {

    @Mock
    private NominatimBoundingBoxMapper boundingBoxMapper;
    private NominatimRegionResponseMapper nominatimRegionResponseMapper;

    @BeforeEach
    public void setUp() {
        nominatimRegionResponseMapper = new NominatimRegionResponseMapper(boundingBoxMapper);
    }

    @Test
    public void testMap() {
        var givenResponse = nominatimRegionResponse();
        var givenBoundingBox = boundingBox();
        when(boundingBoxMapper.map(givenResponse.boundingbox())).thenReturn(givenBoundingBox);

        var actualRegion = nominatimRegionResponseMapper.map(givenResponse);

        var expectedRegion = region();
        assertEquals(expectedRegion, actualRegion);
    }


    @Test
    public void testMapBoundingBox() {
        NominatimBoundingBoxMapper nominatimBoundingBoxMapper = new NominatimBoundingBoxMapper();
        var givenBbox = boundingBoxArray();

        Region.BoundingBox result = nominatimBoundingBoxMapper.map(givenBbox);

        var expectedSouthwest = new Coordinate(givenBbox[0], givenBbox[2]);
        var expectedNortheast = new Coordinate(givenBbox[1], givenBbox[3]);
        assertEquals(expectedSouthwest, result.southWest());
        assertEquals(expectedNortheast, result.northEast());
    }
}
