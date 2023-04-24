package com.example.service_subway.core.model;

import lombok.Value;

public record City(String name, BoundingBox bounds) {
    public record BoundingBox(Coordinate southWest, Coordinate northEast) {
    }
}
