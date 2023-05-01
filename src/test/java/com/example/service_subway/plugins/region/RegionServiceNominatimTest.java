package com.example.service_subway.plugins.region;

import com.example.service_subway.factory.NominatimRegionResponseFactory;
import com.example.service_subway.factory.RegionFactory;
import com.example.service_subway.factory.RegionNameFactory;
import com.example.service_subway.plugins.location.LocationServiceInternal;
import com.example.service_subway.plugins.region.mapper.NominatimRegionResponseMapper;
import com.example.service_subway.plugins.region.model.NominatimRegionResponse;
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

import static com.example.service_subway.factory.NominatimRegionResponseFactory.nominatimRegionResponseArray;
import static com.example.service_subway.factory.RegionFactory.region;
import static com.example.service_subway.factory.RegionNameFactory.regionName;
import static com.example.service_subway.factory.NominatimRegionResponseFactory.nominatimRegionResponse;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceNominatimTest {

    private final String baseUrl = "https://example.com";
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private NominatimRegionResponseMapper mapper;
    @Captor
    private ArgumentCaptor<URI> uriArgumentCaptor;
    private RegionServiceNominatim service;

    @BeforeEach
    void setUp() {
        service = new RegionServiceNominatim(restTemplate, mapper, baseUrl);
    }

    @Test
    public void testGet_success() {
        var givenRegionName = regionName();
        var givenNominatimResponse = nominatimRegionResponse();

        when(mapper.map(givenNominatimResponse)).thenReturn(region());
        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(nominatimRegionResponseArray());

        var actualRegion = service.getByName(givenRegionName).orElseThrow();

        var expectedRegion = region();
        assertEquals(expectedRegion, actualRegion);

        verify(restTemplate, times(1)).getForObject(uriArgumentCaptor.capture(), any());
        var expectedURI = uri(givenRegionName);
        assertEquals(expectedURI, uriArgumentCaptor.getValue());
    }

    @Test
    public void testGet_notFound_shouldReturnEmpty() {
        var givenRegionName = regionName();

        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(null);

        var actualRegion = service.getByName(givenRegionName);
        assertTrue(actualRegion.isEmpty());
    }

    @Test
    public void testGet_throwException_shouldReturnEmpty() {
        var givenRegionName = regionName();

        when(restTemplate.getForObject(any(URI.class), any())).thenThrow(new RuntimeException());

        var actualRegion = service.getByName(givenRegionName);
        assertTrue(actualRegion.isEmpty());
    }

    private URI uri(String regionName) {
        var url = format("%s?q=%s&format=json&polygon_geojson=1&addressdetails=1&limit=1", baseUrl, regionName);
        return UriComponentsBuilder.fromUriString(url)
                .build()
                .toUri();
    }
}
