package com.example.testexample1;

import com.example.testexample1.controller.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WeatherControllerTest {

    @Autowired
    WeatherService weatherService;

    public WeatherControllerTest(){

    }
    public int aa;

    @Test
    public void getForecastTest(){

        String result = null;
        String trueResult = "524901";
        try {
            result = weatherService.getWeather(524901);
            if(!result.contains(trueResult)) {
                throw new Exception("City Id не совпали");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
