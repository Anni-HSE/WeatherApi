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
        this.city = "";
        this.country = "";
        this.description = "";
        this.temp = 0;
        this.windSpeed = 0;
    }

    public WeatherDTO(String city, String country, String description, double temp, double windSpeed) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temp = temp;
        this.windSpeed = windSpeed;
    }
}