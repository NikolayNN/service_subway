package com.example.service_subway.core.exception;

import com.example.service_subway.core.model.Coordinate;

public class LocationNotFoundException extends AppNotFoundException {

    public LocationNotFoundException(Coordinate coordinate) {
        super("Location was not found for coordinate: " + coordinate);
    }
}
