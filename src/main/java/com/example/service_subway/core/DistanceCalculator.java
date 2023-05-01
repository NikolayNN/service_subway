package com.example.service_subway.core;

import com.example.service_subway.core.model.Coordinate;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculator {

    private final static double AVERAGE_RADIUS_OF_EARTH_METERS = 6371000;

    /**
     * calculate distance between two points use haversine formula
     *
     * @param pointA coordinate of first point
     * @param pointB coordinate of second points
     * @return distance in meters
     */
    public double calculate(Coordinate pointA, Coordinate pointB) {
        double latDistance = degToRad(pointA.latitude() - pointB.latitude());
        double lngDistance = degToRad(pointA.longitude() - pointB.longitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(pointA.latitude())) * Math.cos(Math.toRadians(pointB.latitude()))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = AVERAGE_RADIUS_OF_EARTH_METERS * c;
        distance = Math.sqrt(distance * distance);
        return distance;
    }

    private double degToRad(double deg) {
        return deg / 180.0 * Math.PI;
    }
}
