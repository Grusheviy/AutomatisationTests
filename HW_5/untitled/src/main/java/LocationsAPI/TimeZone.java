package LocationsAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeZone {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("GmtOffset")
    private double gmtOffset;
    @JsonProperty("IsDaylightSaving")
    private boolean isDaylightSaving;
    @JsonProperty("NextOffsetChange")
    private String nextOffsetChange;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(double gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public boolean isDaylightSaving() {
        return isDaylightSaving;
    }

    public void setDaylightSaving(boolean daylightSaving) {
        isDaylightSaving = daylightSaving;
    }

    @JsonIgnore
    public String getNextOffsetChange() {
        return nextOffsetChange;
    }
    @JsonIgnore
    public void setNextOffsetChange(String nextOffsetChange) {
        this.nextOffsetChange = nextOffsetChange;
    }
}
