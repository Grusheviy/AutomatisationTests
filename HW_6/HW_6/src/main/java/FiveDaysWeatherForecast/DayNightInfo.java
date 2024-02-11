package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DayNightInfo {
    @JsonProperty
    private int Icon;
    @JsonProperty
    private String IconPhrase;
    @JsonProperty
    private boolean HasPrecipitation;
    @JsonProperty
    private String PrecipitationType;
    @JsonProperty
    private String PrecipitationIntensity;

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getIconPhrase() {
        return IconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        IconPhrase = iconPhrase;
    }

    public boolean isHasPrecipitation() {
        return HasPrecipitation;
    }

    public void setHasPrecipitation(boolean hasPrecipitation) {
        HasPrecipitation = hasPrecipitation;
    }

    public String getPrecipitationType() {
        return PrecipitationType;
    }

    public void setPrecipitationType(String precipitationType) {
        PrecipitationType = precipitationType;
    }

    public String getPrecipitationIntensity() {
        return PrecipitationIntensity;
    }

    public void setPrecipitationIntensity(String precipitationIntensity) {
        PrecipitationIntensity = precipitationIntensity;
    }
}
