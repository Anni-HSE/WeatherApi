package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    Logger log = LoggerFactory.getLogger(WeatherService.class);

    @Value("${key_api}")
    public String key;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String result = null;

    private final WeatherServiceImpl weatherServiceImpl;

    public WeatherService(WeatherServiceImpl weatherServiceImpl){
        this.weatherServiceImpl = weatherServiceImpl;
        this.restTemplate = new RestTemplate();
    }

    public List<Weather> getAll(){
        return weatherServiceImpl.getAll();
    }

    public Weather addForecast(int cityId) throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}&units=metric&lang=ru",
                String.class, cityId, "3efd691599b77b7209f06b0e13067673");

        Weather weather = new Weather();

        JsonNode root = objectMapper.readTree(response.getBody());
        weather.description = root.path("weather").get(0).path("description").asText();
        weather.temp = Double.parseDouble(root.path("main").path("temp").asText());
        weather.temp_min = Double.parseDouble(root.path("main").path("temp_min").asText());
        weather.temp_max = Double.parseDouble(root.path("main").path("temp_max").asText());
        weather.wind_speed = Double.parseDouble(root.path("wind").path("speed").asText());
        weather.country = root.path("sys").path("country").asText();
        weather.city = root.path("name").asText();
        weather.image = weather.description = root.path("weather").get(0).path("icon").asText();


        if (weather != new Weather()) {
            weatherServiceImpl.add(weather);
        }

        return weather;
    }

    public List<Weather> getWeatherByCityId (int cityId) {
        return weatherServiceImpl.getLastWeatherByCityId(cityId);
    }

    public Weather getObjectWeather(int cityId) throws JsonProcessingException {

        Weather weather = new Weather();

        ResponseEntity<String> response = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}&units=metric&lang=ru",
                String.class, cityId, "3efd691599b77b7209f06b0e13067673");

        JsonNode root = objectMapper.readTree(response.getBody());
        weather.description = root.path("weather").get(0).path("description").asText();
        weather.temp = Double.parseDouble(root.path("main").path("temp").asText());
        weather.temp_min = Double.parseDouble(root.path("main").path("temp_min").asText());
        weather.temp_max = Double.parseDouble(root.path("main").path("temp_max").asText());
        weather.wind_speed = Double.parseDouble(root.path("wind").path("speed").asText());
        weather.country = root.path("sys").path("country").asText();
        weather.city = root.path("name").asText();
        weather.image = weather.description = root.path("weather").get(0).path("icon").asText();

        return weather;
    }

    public String getCoordinate(String cityName){

        try {
            result = (String) restTemplate.getForObject("http://api.openweathermap.org/geo/1.0/direct?q={city name}&limit=5&appid={API key}&units=metric",
                    String.class, cityName, "3efd691599b77b7209f06b0e13067673");
        }
        catch (Exception e){

        }
        return result;
    }

    private String convert(ResponseEntity<String> response) throws JsonProcessingException {
        String result = "";

        JsonNode root = objectMapper.readTree(response.getBody());
        result = "Weather: " + String.valueOf(root.path("weather").get(0).path("main").asText()) + "\n" +
                 "Temp: " + String.valueOf(root.path("main").path("temp").asDouble()) + "\n" +
                 "Feels Like: " + String.valueOf(root.path("main").path("feels_like").asDouble()) + "\n" +
                 "Wind speed: " + String.valueOf(root.path("wind").path("speed").asDouble()) + "\n";

        return result;
    }

    private Weather getWeather(ResponseEntity<String> response) throws JsonProcessingException{
        Weather weather = new Weather();
        JsonNode root = objectMapper.readTree(response.getBody());

        weather.cityId = root.path("id").asInt();
        weather.description = root.path("weather").get(0).path("description").toString();
        weather.temp = Double.parseDouble(root.path("main").path("temp").asText());
        weather.temp_min = Double.parseDouble(root.path("main").path("temp_min").asText());
        weather.temp_max = Double.parseDouble(root.path("main").path("temp_max").asText());
        weather.wind_speed = Double.parseDouble(root.path("wind").path("speed").asText());
        weather.country = root.path("sys").path("country").asText();
        weather.city = root.path("name").asText();
        weather.image = weather.description = root.path("weather").get(0).path("icon").toString();

        return  weather;
    }
}
