package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${key_api}")
    public String key;

    @Autowired
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String result = null;

    // Для работы (мне надо, заметки не нашел):
    // 1. Контроллер по геокоординатам +
    // 2. Вынести как поле в класс, инициализировать через констурктор +
    // 3. Распарсить json +
    // 4. Вывести объект на страницу НА РУССКОМ (на html) +
    // 5. Прикрутить сваггер (полноценное RESTfull api) +
    // 6. Нарисовать иконку weatherapi, которая хранится в запросе
    // 7. Попробовать thymeleaf (подключить, сделать контроллер и сделать одну страничку) +
    // 8. Попробовать сделать снова через @Bean +



    private final List<String> forecastList;

    public WeatherService(RestTemplate restTemplate, RestTemplate restTemplate1){
        this.restTemplate = restTemplate;
        this.forecastList = new ArrayList<>();
    }

    public List<String> getForecastList(){
        return this.forecastList;
    }

    public String getWeather(int cityId) throws JsonProcessingException {


        // ResponseEntity<String> response = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}&units=metric&lang=ru",
                // String.class, cityId, "3efd691599b77b7209f06b0e13067673");

        result = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}",
               String.class, cityId, "3efd691599b77b7209f06b0e13067673");

        // forecastList.add(result);

        // return convert(response);

        return result;
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
            forecastList.add(result);
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
                 "Speed: " + String.valueOf(root.path("wind").path("speed").asDouble()) + "\n";

        return result;
    }
}
