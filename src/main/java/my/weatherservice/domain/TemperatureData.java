package my.weatherservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemperatureData {
	private final float temperature;
}
