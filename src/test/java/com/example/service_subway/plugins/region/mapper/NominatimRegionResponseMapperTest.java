package com.example.service_subway.plugins.region.mapper;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Region.BoundingBox;
import com.example.service_subway.plugins.region.mapper.NominatimRegionResponseMapper.NominatimBoundingBoxMapper;
import com.example.service_subway.plugins.region.model.NominatimRegionResponse;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        var expectedRegion = region(givenResponse, givenBoundingBox);
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

    private Region region(NominatimRegionResponse response, BoundingBox bbox) {
        return new Region(response.displayName(), bbox);
    }

    private NominatimRegionResponse nominatimRegionResponse() {
        return new NominatimRegionResponse("region_name", boundingBoxArray());
    }

    private BoundingBox boundingBox() {
        var bboxArray = boundingBoxArray();
        return new BoundingBox(new Coordinate(bboxArray[0], bboxArray[2]), new Coordinate(bboxArray[1], bboxArray[3]));
    }

    private float[] boundingBoxArray() {
        return new float[]{0, 1, 2, 3};
    }
}
