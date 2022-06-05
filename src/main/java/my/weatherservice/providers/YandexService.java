package my.weatherservice.providers;

import my.weatherservice.domain.Converters;
import my.weatherservice.domain.CountryAndCity;
import my.weatherservice.domain.TemperatureData;
import my.weatherservice.providers.ya.client.YaMessageClient;
import my.weatherservice.providers.ya.model.LocationDto;
import my.weatherservice.providers.ya.model.YaMessageDto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YandexService implements IWeatherService {

    private final YaMessageClient client;
        
    public YandexService(YaMessageClient client) {
		this.client = client;
    }

    @Value("${meteo.provider.host:api.weather.yandex.ru}")
    private String host;
    @Value("${meteo.provider.scheme:https}")
    private String scheme;
    @Value("${meteo.provider.path:/v1/forecast/}")
    private String path;

    YaMessageDto requestProvider(LocationDto geo) {
        YaMessageDto dto = null;
        try {
            dto = client.request(getUri(geo));
//            service.saveMessage(dto, geo.getGeonameId());
            log.info("read yandex weather message ok for {}", geo);
            log.debug("response " + dto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ya message is {}", (dto == null ? "null" : String.valueOf(dto)));
        }
        return dto;
    }

    URI getUri(LocationDto geo) {
        return UriComponentsBuilder.newInstance()
            .scheme(scheme)
            .host(host)
            .path(path)
            .queryParam("lat", geo.getLat())
            .queryParam("lon", geo.getLon())
            .buildAndExpand().toUri();
    }

    @Override
    public TemperatureData getForecast(CountryAndCity dto) throws Exception {
    	YaMessageDto rsp = requestProvider(Converters.toLocation(dto));
    	float temp = rsp.getFact().getTemp();
    	return TemperatureData.builder().temperature(temp).build();
    }
}
