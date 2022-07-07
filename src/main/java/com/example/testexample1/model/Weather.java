package com.example.testexample1.model;

import java.io.Serializable;

public class Weather implements Serializable {

    public int cityId;
    public String description;
    public double temp;
    public double temp_min;
    public double temp_max;
    public double wind_speed;
    public String country;
    public String city;
    public String image;
}
