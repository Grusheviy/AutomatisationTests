package DailyDorecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherForecastResponse {
    @JsonProperty
    private Headline Headline;
    @JsonProperty
    private List<DailyForecast> DailyForecasts;

    public Headline getHeadline() {
        return Headline;
    }

    public void setHeadline(Headline headline) {
        Headline = headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return DailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        DailyForecasts = dailyForecasts;
    }
}
