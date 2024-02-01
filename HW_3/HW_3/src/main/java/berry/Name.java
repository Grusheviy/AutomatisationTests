package berry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("language")
    private NamedAPIResource language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public NamedAPIResource getLanguage() {
        return language;
    }

    public void setLanguage(NamedAPIResource language) {
        this.language = language;
    }

    public void setUrl(String url) {
        this.url = url;

    }
}

