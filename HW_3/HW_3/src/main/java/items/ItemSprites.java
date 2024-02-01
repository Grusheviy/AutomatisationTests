package items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemSprites {
    @JsonProperty("default")
    private String defaultSprite;

    public String getDefaultSprite() {
        return defaultSprite;
    }

    public void setDefaultSprite(String defaultSprite) {
        this.defaultSprite = defaultSprite;
    }
}
