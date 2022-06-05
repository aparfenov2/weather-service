package my.weatherservice.providers;

import my.weatherservice.domain.CountryAndCity;
import my.weatherservice.domain.TemperatureData;
import my.weatherservice.providers.owm.List;
import my.weatherservice.providers.owm.OWMForecastResponse;

import java.util.ArrayList;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OwmService implements IWeatherService {

    private final UriComponentsBuilder urlbuilder;
//    private final RestTemplate restTemplate;

    
    public OwmService(RestTemplateBuilder rtBuilder) {
//		this.restTemplate = rtBuilder.additionalInterceptors(new LoggingRequestInterceptor()).build();

		urlbuilder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org")
                .path("/data/2.5/forecast/daily")
                .queryParam("cnt", "1")
                .queryParam("mode", "json")
                .queryParam("units", "metric")
                .queryParam("appid", "212c66a25a472c08ed353270edf23703");
	}

	RestTemplate makeRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        java.util.List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
		return restTemplate;		
	}

    @Override
    public TemperatureData getForecast(CountryAndCity dto) throws Exception {
        String url = urlbuilder.replaceQueryParam("q", dto.getCity()).build().toUriString();
        OWMForecastResponse rsp = makeRestTemplate().getForObject(url, OWMForecastResponse.class);
        float temp = 0.0f;
        for (List pred : rsp.getList()) {
        	temp = pred.getTemp().getDay();
        }
        return TemperatureData.builder().temperature(temp).build();
    }
}
