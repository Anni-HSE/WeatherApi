package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;
import com.example.testexample1.model.WeatherDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public WeatherDTO toDto(Weather weather) {
        String city = weather.getCity();
        String country = weather.getCountry();
        String description = weather.getDescription();
        double temp = weather.getTemp();
        double windSpeed = weather.getWindSpeed();

        return new WeatherDTO(city, country, description, temp, windSpeed);
    }
}
