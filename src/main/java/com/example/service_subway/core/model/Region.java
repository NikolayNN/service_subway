package com.example.service_subway.core.model;

/**
 * Represents a search area to find subway stations.
 * @param name the name of the region
 * @param bounds the bounding box of the region
 */
public record Region(String name, BoundingBox bounds) {

    /**
     * Represents the bounding box of a region
     * defined by its southwest and northeast coordinates.
     *
     * @param southWest the southwest coordinate of the bounding box
     * @param northEast the northeast coordinate of the bounding box
     */
    public record BoundingBox(Coordinate southWest, Coordinate northEast) {
    }
}
