package com.example.testexample1.controller;

import com.example.testexample1.controller.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

@RestController
@RequestMapping(value = "/weather")
@Api(tags = "WeatherController", description = "WeatherController | Test Swagger by Weather")
public class WeatherController {

    WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(path="/getForecastFromCache", produces="application/json")
    public List<String> getForecastFromCache(){
        return this.weatherService.getForecastList();
    }


    @RequestMapping(value = "/forecast={cityId}", method = RequestMethod.GET)
    @ApiOperation(value = "Получить информацию о погоде", notes = "Получить подробную информацию на основе URL-адреса")
    @ApiImplicitParam(name = "cityId", value = "ID", required = true, dataType = "int", paramType = "path")
    public String getForecast(@PathVariable int cityId ) throws JsonProcessingException {

        //Api Key = 3efd691599b77b7209f06b0e13067673
        //Moscow = 524901
        //https://api.openweathermap.org/data/2.5/weather?id=524901&appid=3efd691599b77b7209f06b0e13067673

        String result = weatherService.getWeather(cityId);

        return  result==null?"Request error":result ;
    }

    @RequestMapping(value = "/coordinate={cityName}", method = RequestMethod.GET)
    @ApiOperation(value = "Получить информацию о геокоординатах", notes = "Получить подробную информацию на основе URL-адреса")
    @ApiImplicitParam(name = "cityName", value = "NAME", required = true, dataType = "string", paramType = "path")
    public String getCoordinate(@PathVariable String cityName ){

        //Api Key = 3efd691599b77b7209f06b0e13067673
        //Moscow = 524901
        //https://api.openweathermap.org/data/2.5/weather?id=524901&appid=3efd691599b77b7209f06b0e13067673

        String result = weatherService.getCoordinate(cityName);

        return result==null?"Request error":result ;
    }
}
