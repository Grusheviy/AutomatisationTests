package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Headline {
    @JsonProperty
    private String EffectiveDate;
    @JsonProperty
    private long EffectiveEpochDate;
    @JsonProperty
    private int Severity;
    @JsonProperty
    private String Text;
    @JsonProperty
    private String Category;
    @JsonProperty
    private String EndDate;
    @JsonProperty
    private long EndEpochDate;
    @JsonProperty
    private String MobileLink;
    @JsonProperty
    private String Link;

    public String getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        EffectiveDate = effectiveDate;
    }

    public long getEffectiveEpochDate() {
        return EffectiveEpochDate;
    }

    public void setEffectiveEpochDate(long effectiveEpochDate) {
        EffectiveEpochDate = effectiveEpochDate;
    }

    public int getSeverity() {
        return Severity;
    }

    public void setSeverity(int severity) {
        Severity = severity;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public long getEndEpochDate() {
        return EndEpochDate;
    }

    public void setEndEpochDate(long endEpochDate) {
        EndEpochDate = endEpochDate;
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
