package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
    @JsonProperty
    private String Source;

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }
}