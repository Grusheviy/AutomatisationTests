package CurrentConditionsAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureDetails {
    @JsonProperty("Value")
    private double value;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("UnitType")
    private int unitType;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }
}