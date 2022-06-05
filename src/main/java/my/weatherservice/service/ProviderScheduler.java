
package my.weatherservice.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import my.weatherservice.dao.Location;
import my.weatherservice.dao.WeatherData;
import my.weatherservice.dao.WeatherDataRepository;
import my.weatherservice.domain.Converters;
import my.weatherservice.domain.CountryAndCity;
import my.weatherservice.domain.TemperatureData;
import my.weatherservice.providers.IWeatherService;
import my.weatherservice.providers.ya.model.LocationDto;

@Slf4j
@Service
@ConditionalOnProperty(name = "meteo.scheduling.enabled", havingValue = "true")
public class ProviderScheduler {


    @Autowired
    private List<IWeatherService> services;

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherDataRepository weatherDataRepository;


//    @Scheduled(fixedRate = 1000 * FIXED_RATE, initialDelay = 1000)
    @Scheduled(fixedRateString = "${meteo.scheduling.period_msec}", initialDelay = 1000)
    public void run() throws Exception {
        List<LocationDto> gns = locationService.requestLocations();
        for (final LocationDto gn : gns) {
        	
        	float averageTemp = 0;
            for (IWeatherService svc : services) {
				CountryAndCity geo = Converters.fromLocation(gn);
				TemperatureData temp = svc.getForecast(geo);
				averageTemp += temp.getTemperature();
            }
            averageTemp /= gns.size();
            log.debug("average temp = {}", averageTemp);
			WeatherData weatherData = fromTemp(averageTemp, gn);
			weatherDataRepository.save(weatherData);
        }
    }

	private WeatherData fromTemp(float temp, LocationDto loc) {
		WeatherData wd = WeatherData.builder()
				.date(LocalDate.now())
				.location(Location.builder().city(loc.getGeoname()).build())
				.temperature(Arrays.asList((double)temp))
				.build();
		return wd;
	}

}
