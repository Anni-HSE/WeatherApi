package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class WeatherService {

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

        Weather weather = createWeather(response);

        if (weather != new Weather()) {
            weatherServiceImpl.add(weather);
        }

        return weather;
    }

    public List<Weather> getWeatherByCityId (int cityId) {
        return weatherServiceImpl.getLastWeatherByCityId(cityId);
    }

    public Weather getObjectWeather(int cityId) throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}&units=metric&lang=ru",
                String.class, cityId, "3efd691599b77b7209f06b0e13067673");

        Weather weather = createWeather(response);

        if (weather != new Weather()) {
            weatherServiceImpl.add(weather);
        }

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

    private Weather createWeather(ResponseEntity<String> response) throws JsonProcessingException{

        Weather weather = new Weather();

        JsonNode root = objectMapper.readTree(response.getBody());
        weather.setDescription(root.path("weather").get(0).path("description").asText());
        weather.setTemp(Double.parseDouble(root.path("main").path("temp").asText()));
        weather.setTemp_min(Double.parseDouble(root.path("main").path("temp_min").asText()));
        weather.setTemp_max(Double.parseDouble(root.path("main").path("temp_max").asText()));
        weather.setWind_speed(Double.parseDouble(root.path("wind").path("speed").asText()));
        weather.setCountry(root.path("sys").path("country").asText());
        weather.setCity(root.path("name").asText());
        weather.setImage(root.path("weather").get(0).path("icon").asText());
        weather.setCityId(Integer.parseInt(root.path("id").asText()));

        return  weather;
    }
}
