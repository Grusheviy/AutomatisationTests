package LocationsAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
        "Version",
        "Key",
        "Type",
        "Rank",
        "LocalizedName",
        "EnglishName",
        "PrimaryPostalCode",
        "Region",
        "Country",
        "AdministrativeArea",
        "TimeZone",
        "GeoPosition",
        "IsAlias",
        "SupplementalAdminAreas",
        "DataSets"
})
public class TopCities {

    @JsonProperty("Version")
    private int version;

    @JsonProperty("Key")
    private String key;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Rank")
    private int rank;

    @JsonProperty("LocalizedName")
    private String localizedName;

    @JsonProperty("EnglishName")
    private String englishName;

    @JsonProperty("PrimaryPostalCode")
    private String primaryPostalCode;

    @JsonProperty("Region")
    private Region region;

    @JsonProperty("Country")
    private Country country;

    @JsonProperty("AdministrativeArea")
    private AdminArea administrativeArea;

    @JsonProperty("TimeZone")
    private TimeZone timeZone;

    @JsonProperty("GeoPosition")
    private GeoPosition geoPosition;

    @JsonProperty("IsAlias")
    private boolean isAlias;

    @JsonProperty("SupplementalAdminAreas")
    private List<Object> supplementalAdminAreas;

    @JsonProperty("DataSets")
    private List<String> dataSets;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public void setPrimaryPostalCode(String primaryPostalCode) {
        this.primaryPostalCode = primaryPostalCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdminArea getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(AdminArea administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public boolean isAlias() {
        return isAlias;
    }

    public void setAlias(boolean alias) {
        isAlias = alias;
    }

    public List<Object> getSupplementalAdminAreas() {
        return supplementalAdminAreas;
    }

    public void setSupplementalAdminAreas(List<Object> supplementalAdminAreas) {
        this.supplementalAdminAreas = supplementalAdminAreas;
    }

    public List<String> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<String> dataSets) {
        this.dataSets = dataSets;
    }
}