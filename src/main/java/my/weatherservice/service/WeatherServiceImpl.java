package my.weatherservice.service;

import my.weatherservice.DateUtils;
import my.weatherservice.dao.WeatherData;
import my.weatherservice.dao.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private static final String FORMAT_EXCEPTION =  "Format exception. Data must have the following format: yyyy-MM-dd";
    private static final String WEATHER_NOT_FOUND_EXCEPTION = "Weather data not found";
    private static final String WEATHER_DELETED_MESSAGE = "Weather data successfully deleted";

    @Override
    public ResponseEntity<Object> getWeather(String coutry, String city, String date) throws DateTimeParseException {

        if (date != null && !date.isEmpty()){
            try{
                List<WeatherData> weatherData = weatherDataRepository.findByDateAndLocation_City(DateUtils.formatDate(date), city);
                return weatherData != null && weatherData.size() > 0 ? new ResponseEntity<>(weatherData, HttpStatus.OK) : new ResponseEntity<>(WEATHER_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND);
            } catch (DateTimeParseException e){
                return new ResponseEntity<>(FORMAT_EXCEPTION, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            List<WeatherData> weatherData = weatherDataRepository.findByLocation_CityOrderByIdDesc(city);
            return weatherData != null && weatherData.size() > 0 ? new ResponseEntity<>(weatherData, HttpStatus.OK) : new ResponseEntity<>(WEATHER_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND);        	
        }
    }

//    @Override
//    public ResponseEntity<Object> getWeatherByParam(Long id) {
//
//        Optional<WeatherData> optionalWeatherData = weatherDataRepository.findById(id);
//        return optionalWeatherData.<ResponseEntity<Object>>map(weatherData ->
//                new ResponseEntity<>(weatherData, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(WEATHER_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND));
//    }
//
//
//    @Override
//    public ResponseEntity<Object> postWeather(WeatherData weatherData) {
//
//        Optional<WeatherData> optionalWeatherData = weatherDataRepository.findById(weatherData.getId());
//        if (optionalWeatherData.isPresent())
//            return new ResponseEntity<>("Error: duplicated data id", HttpStatus.BAD_REQUEST);
//        else {
//            weatherDataRepository.save(weatherData);
//            return new ResponseEntity<>("Data successfully saved", HttpStatus.CREATED);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> deleteAll() {
//
//        weatherDataRepository.deleteAll();
//        return new ResponseEntity<>(WEATHER_DELETED_MESSAGE, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<Object> deleteWeatherById(Long id) {
//
//        Optional<WeatherData> optionalWeatherData = weatherDataRepository.findById(id);
//        if (optionalWeatherData.isPresent()) {
//            weatherDataRepository.deleteById(id);
//            return new ResponseEntity<>(WEATHER_DELETED_MESSAGE, HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(WEATHER_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND);
//    }

}
