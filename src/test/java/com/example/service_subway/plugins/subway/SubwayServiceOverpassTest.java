package com.example.service_subway.plugins.subway;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Region;
import com.example.service_subway.factory.OverpassSubwayResponseFactory;
import com.example.service_subway.factory.SubwayFactory;
import com.example.service_subway.plugins.region.RegionServiceNominatim;
import com.example.service_subway.plugins.region.mapper.NominatimRegionResponseMapper;
import com.example.service_subway.plugins.subway.mapper.OverpassSubwayResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.service_subway.factory.NominatimRegionResponseFactory.nominatimRegionResponse;
import static com.example.service_subway.factory.NominatimRegionResponseFactory.nominatimRegionResponseArray;
import static com.example.service_subway.factory.OverpassSubwayResponseFactory.overpassSubwayResponse;
import static com.example.service_subway.factory.RegionFactory.region;
import static com.example.service_subway.factory.RegionNameFactory.regionName;
import static com.example.service_subway.factory.SubwayFactory.subways;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubwayServiceOverpassTest {

    private final String baseUrl = "https://example.com";
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private OverpassSubwayResponseMapper mapper;
    @Captor
    private ArgumentCaptor<URI> uriArgumentCaptor;
    private SubwayServiceOverpass service;

    @BeforeEach
    void setUp() {
        service = new SubwayServiceOverpass(restTemplate, mapper, baseUrl);
    }

    @Test
    public void testGet_success() {
        var givenRegion = region();
        var givenOverpassSubwayResponse = overpassSubwayResponse();
        var givenSubways = subways();

        when(mapper.map(givenOverpassSubwayResponse)).thenReturn(givenSubways);
        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(givenOverpassSubwayResponse);

        var actualSubways = service.listByCity(givenRegion);

        var expectedSubways = subways();
        assertEquals(expectedSubways, actualSubways);

        verify(restTemplate, times(1)).getForObject(uriArgumentCaptor.capture(), any());
        var expectedURI = uri(givenRegion);
        assertEquals(expectedURI, uriArgumentCaptor.getValue());
    }

    @Test
    public void testGet_throwException_shouldReturnEmptyList() {
        var givenRegion = region();

        when(restTemplate.getForObject(any(URI.class), any())).thenThrow(new RuntimeException());

        var actualSubways = service.listByCity(givenRegion);
        assertEquals(List.of(), actualSubways);
    }

    private URI uri(Region region) {
        var url = format("%s?data=[out:json];node[station=subway](%s);out;", baseUrl, toString(region));
        return UriComponentsBuilder.fromUriString(url)
                .build()
                .toUri();
    }

    private String toString(Region region) {
        return toString(region.bounds().southWest()) + "," + toString(region.bounds().northEast());
    }

    private String toString(Coordinate coordinate) {
        return coordinate.latitude() + "," + coordinate.longitude();
    }

}
