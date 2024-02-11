package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty
    private TemperatureInfo Minimum;
    @JsonProperty
    private TemperatureInfo Maximum;

    public TemperatureInfo getMinimum() {
        return Minimum;
    }

    public void setMinimum(TemperatureInfo minimum) {
        Minimum = minimum;
    }

    public TemperatureInfo getMaximum() {
        return Maximum;
    }

    public void setMaximum(TemperatureInfo maximum) {
        Maximum = maximum;
    }
}
