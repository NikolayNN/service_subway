package com.example.service_subway.core.exception;

import com.example.service_subway.core.model.Location;

public class SubwayNotFoundException extends AppNotFoundException {

    public SubwayNotFoundException(Location location) {
        super("Subways was not found in location: " + location);
    }

}
