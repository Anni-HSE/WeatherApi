package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;

import java.util.List;

public interface WeatherInterface {

    List<Weather> getAll();

    Weather getOne(int cityId);

    void add(Weather weather);
}
