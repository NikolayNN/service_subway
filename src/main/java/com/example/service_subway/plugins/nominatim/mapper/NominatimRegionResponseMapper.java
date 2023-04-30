package com.example.service_subway.plugins.nominatim.mapper;

import com.example.service_subway.core.model.Region;
import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.plugins.nominatim.model.NominatimRegionResponse;
import com.example.service_subway.core.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NominatimRegionResponseMapper implements Mapper<NominatimRegionResponse, Region> {

    private final NominatimBoundingBoxMapper boundingBoxMapper;

    @Override
    public Region map(NominatimRegionResponse response) {
        return new Region(
                response.displayName(),
                boundingBoxMapper.map(response.boundingbox()));
    }

    @Component
    public static class NominatimBoundingBoxMapper implements Mapper<float[], Region.BoundingBox> {

        @Override
        public Region.BoundingBox map(float[] bbox) {
            return new Region.BoundingBox(new Coordinate(bbox[0], bbox[2]), new Coordinate(bbox[1], bbox[3]));
        }
    }
}
