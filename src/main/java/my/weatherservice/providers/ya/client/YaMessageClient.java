/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package my.weatherservice.providers.ya.client;

import java.io.IOException;
import java.net.URI;

import my.weatherservice.providers.ya.model.YaMessageDto;

public interface YaMessageClient {

    YaMessageDto request(URI uri) throws IOException;

}
