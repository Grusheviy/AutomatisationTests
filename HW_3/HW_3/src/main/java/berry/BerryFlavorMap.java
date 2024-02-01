package berry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BerryFlavorMap {

    @JsonProperty("potency")
    private Integer potency;
    @JsonProperty("flavor")
    private NamedAPIResource flavor;

    public Integer getPotency() {
        return potency;
    }

    public void setPotency(Integer potency) {
        this.potency = potency;
    }

    public NamedAPIResource getFlavor() {
        return flavor;
    }

    public void setFlavor(NamedAPIResource flavor) {
        this.flavor = flavor;
    }
}
