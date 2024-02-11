package FiveDaysWeatherForecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureInfo {
    @JsonProperty
    private int Value;
    @JsonProperty
    private String Unit;
    @JsonProperty
    private int UnitType;

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getUnitType() {
        return UnitType;
    }

    public void setUnitType(int unitType) {
        UnitType = unitType;
    }
}