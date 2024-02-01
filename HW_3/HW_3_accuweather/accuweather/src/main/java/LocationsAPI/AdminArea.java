package LocationsAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminArea {
    @JsonProperty("ID")
    private String id;

    @JsonProperty("LocalizedName")
    private String localizedName;

    @JsonProperty("EnglishName")
    private String englishName;

    @JsonProperty("Level")
    private int level;

    @JsonProperty("LocalizedType")
    private String localizedType;

    @JsonProperty("EnglishType")
    private String englishType;

    @JsonProperty("CountryID")
    private String countryID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLocalizedType() {
        return localizedType;
    }

    public void setLocalizedType(String localizedType) {
        this.localizedType = localizedType;
    }

    public String getEnglishType() {
        return englishType;
    }

    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}

