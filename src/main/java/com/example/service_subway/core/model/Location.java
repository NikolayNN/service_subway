package com.example.service_subway.core.model;

/**
 * Represents the location of a user.
 * @param coordinate the coordinate of the location
 * @param country the country of the location
 * @param city the city of the location
 */
public record Location(Coordinate coordinate, String country, String city) {
}
