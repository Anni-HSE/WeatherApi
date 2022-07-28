package com.example.testexample1.controller;

import com.example.testexample1.controller.service.Mapper;
import com.example.testexample1.controller.service.WeatherService;
import com.example.testexample1.model.Weather;
import com.example.testexample1.model.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/weather")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@Api(tags = "WeatherController", description = "WeatherController | Test Swagger by Weather")
public class WeatherController {

    private WeatherService weatherService;
    private Mapper mapper;

    @Autowired
    public WeatherController(WeatherService weatherService, Mapper mapper){
        this.weatherService = weatherService;
        this.mapper = mapper;
    }

    @ResponseBody
    @GetMapping(path = "/getWeathers", produces = "application/json")
    @ApiOperation(value = "Получить всю погоду", notes = "Получить подробную информацию на основе URL-адреса")
    public List<WeatherDTO> getWeathers() {
        return weatherService.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping(path = "/getWeather={cityId}", produces = "application/json")
    @ApiOperation(value = "Получить последний прогноз погоды для текущего города", notes = "Получить подробную информацию на основе URL-адреса")
    @ApiImplicitParam(name = "cityId", value = "ID", required = true, dataType = "int", paramType = "path")
    public Weather getWeatherByCityId(@PathVariable int cityId) {
        List<Weather> weathers = weatherService.getWeatherByCityId(cityId);
        return weathers.get(weathers.size() - 1);
    }

    @RequestMapping(value = "/forecast={cityId}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Получить информацию о погоде", notes = "Получить подробную информацию на основе URL-адреса")
    @ApiImplicitParam(name = "cityId", value = "ID", required = true, dataType = "int", paramType = "path")
    public WeatherDTO getForecast(@PathVariable int cityId ) throws JsonProcessingException {
        //Api Key = 3efd691599b77b7209f06b0e13067673
        //Moscow = 524901
        //https://api.openweathermap.org/data/2.5/weather?id=524901&appid=3efd691599b77b7209f06b0e13067673

        WeatherDTO weatherDTO = mapper.toDto(weatherService.addForecast(cityId));

        return  weatherDTO==null?new WeatherDTO():weatherDTO ;
    }

    @RequestMapping(value = "/coordinate={cityName}", method = RequestMethod.GET)
    @ApiOperation(value = "Получить информацию о геокоординатах", notes = "Получить подробную информацию на основе URL-адреса")
    @ApiImplicitParam(name = "cityName", value = "NAME", required = true, dataType = "string", paramType = "path")
    public String getCoordinate(@PathVariable String cityName ){
        String result = weatherService.getCoordinate(cityName);
        return result==null?"Request error":result ;
    }
}
