package DailyDorecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureInfo {
    @JsonProperty
    private double Value;
    @JsonProperty
    private String Unit;
    @JsonProperty
    private int UnitType;

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
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