package com.example.testexample1;

import com.example.testexample1.controller.service.WeatherService;
import com.example.testexample1.model.Weather;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WeatherControllerTest {

    Logger log = LoggerFactory.getLogger(WeatherControllerTest.class);

    @Autowired
    WeatherService weatherService;

    @Test
    public void getForecastTest(){

        Weather result;
        int trueResult = 524901;
        try {
            result = weatherService.addForecast(trueResult);
            assertTrue(result.getCityId() == trueResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getCoordinateTest(){

        String result;
        String cityName = "Perm";
        try {
            result = weatherService.getCoordinate(cityName);
            assertTrue(result.contains(String.valueOf(cityName)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
