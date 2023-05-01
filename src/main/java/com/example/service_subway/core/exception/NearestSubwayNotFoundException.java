package com.example.service_subway.core.exception;

import com.example.service_subway.core.model.Location;

public class NearestSubwayNotFoundException extends AppNotFoundException {
    public NearestSubwayNotFoundException(Location location, int radius) {
        super("Nearest subway was not found in location: " + location + " in radius: " + radius + " meters");
    }
}
