package com.example.service_subway.core.model;

/**
 * Represents a subway station.
 *
 * @param name       the name of the subway station
 * @param color      the color of the subway line
 * @param coordinate the GPS coordinates of the subway station
 */
public record Subway(String name, String color, Coordinate coordinate) {
}
