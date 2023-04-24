package com.example.service_subway.core.plugins;

import com.example.service_subway.core.model.City;
import com.example.service_subway.core.model.Subway;

import java.util.List;

public interface SubwayService {
    List<Subway> listByCity(City city);
}
