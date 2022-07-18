package com.example.testexample1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "")
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "sds")
    public int id;

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
