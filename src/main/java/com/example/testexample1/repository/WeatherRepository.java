package com.example.testexample1.repository;

import com.example.testexample1.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findByCityId(int cityId);
}
