package com.example.service_subway.plugins.city.mapper;

import com.example.service_subway.core.model.City;
import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.plugins.city.model.NominatimCityResponse;
import com.example.service_subway.core.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NominatimCityResponseMapper implements Mapper<NominatimCityResponse, City> {

    private final NominatimBoundingBoxMapper boundingBoxMapper;

    @Override
    public City map(NominatimCityResponse response) {
        return new City(
                response.displayName(),
                boundingBoxMapper.map(response.boundingbox()));
    }

    @Component
    public static class NominatimBoundingBoxMapper implements Mapper<float[], City.BoundingBox> {

        @Override
        public City.BoundingBox map(float[] bbox) {
            return new City.BoundingBox(new Coordinate(bbox[0], bbox[2]), new Coordinate(bbox[1], bbox[3]));
        }
    }
}
