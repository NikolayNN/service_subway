package com.example.service_subway.core.mapper;

public interface Mapper<FROM, TO> {
    TO map(FROM from);
}
