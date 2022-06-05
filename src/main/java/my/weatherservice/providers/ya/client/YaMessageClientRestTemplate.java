/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package my.weatherservice.providers.ya.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import my.weatherservice.providers.LoggingRequestInterceptor;
import my.weatherservice.providers.ya.model.YaMessageDto;

@Slf4j
//@ConditionalOnProperty(name = "meteo.provider.type", havingValue = "resttemplate", matchIfMissing = true)
@Component
public class YaMessageClientRestTemplate implements YaMessageClient {

//    private final RestTemplate restTemplate;

    @Value("${meteo.provider.key}")
    private String apiKey;

    public YaMessageClientRestTemplate(RestTemplateBuilder rtBuilder) {
//        this.restTemplate = rtBuilder
//        		.additionalInterceptors(new LoggingRequestInterceptor())
//        		.additionalInterceptors((request, body, execution) -> {
//            request.getHeaders().add("X-Yandex-API-Key", apiKey);
//            return execution.execute(request, body);
//        }).build();
    }

	RestTemplate makeRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        java.util.List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ClientHttpRequestInterceptor() {
			
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				 request.getHeaders().add("X-Yandex-API-Key", apiKey);
				 return execution.execute(request, body);
			}
		});
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
		return restTemplate;		
	}

    @Override
    public YaMessageDto request(URI uri) {
        return makeRestTemplate().getForObject(uri, YaMessageDto.class);
    }

    @PostConstruct
    void init() {
        log.info(this.getClass().getCanonicalName() + " inited");
    }
}
