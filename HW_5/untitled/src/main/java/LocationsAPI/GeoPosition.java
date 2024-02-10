package LocationsAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoPosition {
    @JsonProperty("Latitude")
    private double latitude;

    @JsonProperty("Longitude")
    private double longitude;

    @JsonProperty("Elevation")
    private Elevation elevation;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Elevation getElevation() {
        return elevation;
    }

    public void setElevation(Elevation elevation) {
        this.elevation = elevation;
    }
}
