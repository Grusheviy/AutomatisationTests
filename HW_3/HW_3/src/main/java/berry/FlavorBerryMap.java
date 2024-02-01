package berry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlavorBerryMap {

    @JsonProperty("potency")
    private Integer potency;
    @JsonProperty("berry")
    private NamedAPIResource berry;

    public Integer getPotency() {
        return potency;
    }

    public void setPotency(Integer potency) {
        this.potency = potency;
    }

    public NamedAPIResource getBerry() {
        return berry;
    }

    public void setBerry(NamedAPIResource berry) {
        this.berry = berry;
    }
}
