/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package my.weatherservice.providers.ya.api;

import java.util.List;

import my.weatherservice.providers.ya.model.YaFactDto;
import my.weatherservice.providers.ya.model.YaMessageDto;

public interface YaRestResource {

    List<YaMessageDto> getMessages(Integer geonameId, String dateFrom, String dateTo);

    YaMessageDto getMessage(String uuid);

    YaMessageDto getLastMessage(Integer geonameId);

    List<YaFactDto> getFacts(Integer geonameId, String dateFrom, String dateTo);
}
