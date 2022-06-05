package my.weatherservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Object> getWeather(
    		@RequestParam(required = true) String country, 
    		@RequestParam(required = true) String city, 
    		@RequestParam(required = false) String date
    		){
        return weatherService.getWeather(country, city, date);
    }

//    @GetMapping("/weather/{weatherId}")
//    public ResponseEntity<Object> getWeather(@PathVariable(value = "weatherId") Long weatherId){
//        return weatherService.getWeatherByParam(weatherId);
//    }

//    @PostMapping("/weather")
//    public ResponseEntity<Object> postWeather(@RequestBody WeatherData weatherData){
//        return weatherService.postWeather(weatherData);
//    }
//
//    @DeleteMapping("/weather")
//    public ResponseEntity<Object> deleteAllData(){
//        return weatherService.deleteAll();
//    }
//
//    @DeleteMapping("/weather/{weatherId}")
//    public ResponseEntity<Object> deleteWeather(@PathVariable(value = "weatherId") Long weatherId){
//        return weatherService.deleteWeatherById(weatherId);
//    }

}
