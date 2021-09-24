package com.training.weatherservice;

import com.training.weatherservice.dao.WeatherDataRepository;
import com.training.weatherservice.domain.WeatherData;
import com.training.weatherservice.services.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class WeatherServiceApplicationTests {

	@InjectMocks
	WeatherServiceImpl weatherService;

	@Mock
	WeatherData weatherData;

	@Mock
	WeatherDataRepository weatherDataRepository;

	private final String date = "2020-09-15";
	private final String wrongFormattedDate = "2020.09.15";

	@Test
	void contextLoads() {
	}

	@Test
	void getWeatherByDateOk() throws ParseException {

		List<WeatherData> weatherDataList = new ArrayList<>();
		weatherDataList.add(weatherData);
		Mockito.when(weatherDataRepository.findByDate(Mockito.any())).thenReturn(weatherDataList);
		Assertions.assertEquals(new ResponseEntity<>(weatherDataList, HttpStatus.OK), weatherService.getWeather(date));
	}

	@Test
	void getWeatherByDateNotFound() throws ParseException {

		Mockito.when(weatherDataRepository.findByDate(Mockito.any())).thenReturn(null);
		Assertions.assertEquals(new  ResponseEntity<>("Weather data not found", HttpStatus.NOT_FOUND), weatherService.getWeather(date));
	}

	@Test
	void getAllWeatherOk() throws ParseException {

		List<WeatherData> weatherDataList = new ArrayList<>();
		weatherDataList.add(weatherData);
		Mockito.when(weatherDataRepository.findAll()).thenReturn(weatherDataList);
		Assertions.assertEquals(new ResponseEntity<>(weatherDataList, HttpStatus.OK), weatherService.getWeather(""));
	}

	@Test
	void deleteAllWeathers(){

		Mockito.doNothing().when(weatherDataRepository).deleteAll();
		Assertions.assertEquals(new ResponseEntity<>("Weather data successfully deleted", HttpStatus.OK), weatherService.deleteAll());
	}


}
