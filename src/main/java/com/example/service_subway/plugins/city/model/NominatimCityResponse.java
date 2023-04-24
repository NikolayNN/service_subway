package com.example.service_subway.plugins.city.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NominatimCityResponse(
        @JsonProperty("display_name") String displayName,
        float[] boundingbox) {
}
