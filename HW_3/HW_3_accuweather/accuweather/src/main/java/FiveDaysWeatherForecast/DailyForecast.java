package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyForecast {
    @JsonProperty
    private String Date;
    @JsonProperty
    private long EpochDate;
    @JsonProperty
    private Temperature Temperature;
    @JsonProperty
    private DayNightInfo Day;
    @JsonProperty
    private DayNightInfo Night;
    @JsonProperty
    private List<String> Sources;
    @JsonProperty
    private String MobileLink;
    @JsonProperty
    private String Link;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public long getEpochDate() {
        return EpochDate;
    }

    public void setEpochDate(long epochDate) {
        EpochDate = epochDate;
    }

    public Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(Temperature temperature) {
        Temperature = temperature;
    }

    public DayNightInfo getDay() {
        return Day;
    }

    public void setDay(DayNightInfo day) {
        Day = day;
    }

    public DayNightInfo getNight() {
        return Night;
    }

    public void setNight(DayNightInfo night) {
        Night = night;
    }

    public List<String> getSources() {
        return Sources;
    }

    public void setSources(List<String> sources) {
        Sources = sources;
    }

    public String getMobileLink() {
        return MobileLink;
    }

    public void setMobileLink(String mobileLink) {
        MobileLink = mobileLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
