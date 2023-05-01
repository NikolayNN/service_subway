package com.example.service_subway.plugins.location;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Location;
import com.example.service_subway.plugins.location.LocationServiceInternal.LocationResponse;
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
import java.util.Optional;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceInternalTest {
    private final String baseUrl = "https://example.com";
    @Mock
    private RestTemplate restTemplate;
    @Captor
    private ArgumentCaptor<URI> uriArgumentCaptor;
    private LocationServiceInternal service;


    @BeforeEach
    void setUp() {
        service = new LocationServiceInternal(restTemplate, baseUrl);
    }

    @Test
    public void testGet_success() {
        var givenCoordinate = coordinate();
        var givenResponse = locationResponse();
        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(givenResponse);

        var actualLocation = service.get(givenCoordinate).orElseThrow();

        var expectedLocation = location(givenCoordinate, givenResponse);
        assertEquals(expectedLocation, actualLocation);

        verify(restTemplate, times(1)).getForObject(uriArgumentCaptor.capture(), any());
        var expectedURI = uri();
        assertEquals(expectedURI, uriArgumentCaptor.getValue());
    }

    @Test
    public void testGet_notFound_shouldReturnEmptyOptional() {
        var givenCoordinate = coordinate();
        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(null);

        var actualLocation = service.get(givenCoordinate);
        assertTrue(actualLocation.isEmpty());
    }

    @Test
    public void testGet_throwException_shouldReturnEmptyOptional() {
        var givenCoordinate = coordinate();
        when(restTemplate.getForObject(any(URI.class), any())).thenThrow(new RuntimeException());

        var actualLocation = service.get(givenCoordinate);
        assertTrue(actualLocation.isEmpty());
    }

    private URI uri() {
        var url = format("%s?lat=%s&lon=%s", baseUrl, coordinate().latitude(), coordinate().longitude());
        return UriComponentsBuilder.fromUriString(url)
                .build()
                .toUri();
    }

    private Coordinate coordinate() {
        return new Coordinate(53.90F, 27.53F);
    }

    private LocationResponse locationResponse() {
        return new LocationResponse("Беларусь", "Минск");
    }

    private Location location(Coordinate coordinate, LocationResponse response) {
        return new Location(coordinate, response.country(), response.city());
    }
}
