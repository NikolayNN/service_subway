package com.example.service_subway.plugins.region.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NominatimRegionResponse(
        @JsonProperty("display_name") String displayName,
        float[] boundingbox) {
}
