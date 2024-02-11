package CurrentConditionsAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("Metric")
    private TemperatureDetails metric;

    @JsonProperty("Imperial")
    private TemperatureDetails imperial;

    public TemperatureDetails getMetric() {
        return metric;
    }

    public void setMetric(TemperatureDetails metric) {
        this.metric = metric;
    }

    public TemperatureDetails getImperial() {
        return imperial;
    }

    public void setImperial(TemperatureDetails imperial) {
        this.imperial = imperial;
    }
}