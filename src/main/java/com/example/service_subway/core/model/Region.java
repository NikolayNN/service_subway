package com.example.service_subway.core.model;

public record Region(String name, BoundingBox bounds) {
    public record BoundingBox(Coordinate southWest, Coordinate northEast) {
    }
}
