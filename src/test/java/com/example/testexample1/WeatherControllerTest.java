package com.example.testexample1;

import com.example.testexample1.controller.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WeatherControllerTest {

    Logger log = LoggerFactory.getLogger(WeatherControllerTest.class);

    @Autowired
    WeatherService weatherService;

    @Test
    public void getForecastTest(){

        String result;
        int trueResult = 524901;
        try {
            result = weatherService.getWeather(trueResult);
            assertTrue(result.contains(String.valueOf(trueResult)));
            /*
            if(true || result==null || !result.contains(String.valueOf(trueResult))) {
                throw new Exception("City Id не совпали");
            }else {
                log.debug("City Id found");
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
