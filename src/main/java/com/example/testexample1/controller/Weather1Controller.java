package com.example.testexample1.controller;

import com.example.testexample1.controller.service.WeatherService;
import com.example.testexample1.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Weather1Controller {

    WeatherService weatherService;

    @Autowired
    public Weather1Controller(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "weather1={cityId}", method = RequestMethod.GET)
    public String weather(@PathVariable int cityId, Model model) throws JsonProcessingException {
        Weather weather = weatherService.getObjectWeather(cityId);
        model.addAttribute("weather", weather);


        return "weather.html";
    }
}
