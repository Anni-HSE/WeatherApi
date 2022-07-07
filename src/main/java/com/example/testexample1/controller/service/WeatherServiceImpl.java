package com.example.testexample1.controller.service;

import com.example.testexample1.model.Weather;
import com.example.testexample1.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WeatherServiceImpl implements WeatherInterface{

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> getAll(){
        return weatherRepository.findAll();
    }

    @Override
    public Weather getOne(int cityId){
        return weatherRepository.findByCityId(cityId);
    }

    @Override
    public void add(Weather weather){
        weatherRepository.save(weather);
    }
}
