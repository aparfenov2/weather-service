package com.training.weatherservice.services;

import com.training.weatherservice.domain.WeatherData;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeParseException;

public interface WeatherService {

    ResponseEntity<Object> getWeather(String date) throws DateTimeParseException;

    ResponseEntity<Object> getWeatherByParam(Long id);

    ResponseEntity<Object> postWeather(WeatherData weatherData);

    ResponseEntity<Object> deleteAll();

    ResponseEntity<Object> deleteWeatherById(Long id);

}
