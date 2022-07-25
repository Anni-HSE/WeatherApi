package com.example.testexample1.repository;

import com.example.testexample1.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findByCityId(int cityId);

    @Query(value = "SELECT * FROM Weather where cityId = :cityId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    List<Weather> getLastWeatherById(@Param("cityId") int cityId);
}
