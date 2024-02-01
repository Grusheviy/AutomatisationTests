package berry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorBerryMap {

    @JsonProperty("potency")
    private Integer potency;
    @JsonProperty("berry")
    private NamedAPIResource berry;
    public Integer getPotency() {
        return potency;
    }
    public void setPotency(Integer potency) {
        this.potency = potency;}
    public NamedAPIResource getBerry() {
        return berry;}
    public void setBerry(NamedAPIResource berry) {
        this.berry = berry;
    }
}
