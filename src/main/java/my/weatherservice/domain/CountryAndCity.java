package my.weatherservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryAndCity {
    private final double lat;
    private final double lon;
	private final String country;
	private final String city;
}
