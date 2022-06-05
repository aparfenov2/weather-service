package my.weatherservice.providers;

import my.weatherservice.domain.CountryAndCity;
import my.weatherservice.domain.TemperatureData;

/**
 * Created by Nico on 27.02.2017.
 */

public interface IWeatherService {
    public TemperatureData getForecast(CountryAndCity dto) throws Exception;
}
