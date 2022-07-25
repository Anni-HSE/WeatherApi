package com.example.testexample1.model;

import javax.persistence.*;
import java.io.Serializable;

public class WeatherDTO implements Serializable {

    public String city;
    public String country;
    public String description;
    public double temp;
    public double windSpeed;

    public WeatherDTO() {
        city = "";
        country = "";
        description = "";
        temp = 0;
        windSpeed = 0;
    }

    public WeatherDTO(String _city, String _country, String _description, double _temp, double _windSpeed) {
        city = _city;
        country = _country;
        description = _description;
        temp = _temp;
        windSpeed = _windSpeed;
    }
}