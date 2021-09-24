package com.training.weatherservice.dao;

import com.training.weatherservice.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findByDate(LocalDate date);

}
