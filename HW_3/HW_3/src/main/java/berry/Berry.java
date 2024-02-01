package berry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "growth_time",
        "max_harvest",
        "natural_gift_power",
        "size",
        "smoothness",
        "soil_dryness",
        "firmness",
        "flavors",
        "item",
        "natural_gift_type"
})
public class Berry {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("growth_time")
    private Integer growthTime;
    @JsonProperty("max_harvest")
    private Integer maxHarvest;
    @JsonProperty("natural_gift_power")
    private Integer naturalGiftPower;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("smoothness")
    private Integer smoothness;
    @JsonProperty("soil_dryness")
    private Integer soilDryness;
    @JsonProperty("firmness")
    private NamedAPIResource firmness;
    @JsonProperty("flavors")
    private List<BerryFlavorMap> flavors = null;
    @JsonProperty("item")
    private NamedAPIResource item;
    @JsonProperty("natural_gift_type")
    private NamedAPIResource naturalGiftType;
    @JsonIgnore
    private Map<String, Object> additionalProperties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
        this.growthTime = growthTime;
    }

    public Integer getMaxHarvest() {
        return maxHarvest;
    }

    public void setMaxHarvest(Integer maxHarvest) {
        this.maxHarvest = maxHarvest;
    }

    public Integer getNaturalGiftPower() {
        return naturalGiftPower;
    }

    public void setNaturalGiftPower(Integer naturalGiftPower) {
        this.naturalGiftPower = naturalGiftPower;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(Integer smoothness) {
        this.smoothness = smoothness;
    }

    public Integer getSoilDryness() {
        return soilDryness;
    }

    public void setSoilDryness(Integer soilDryness) {
        this.soilDryness = soilDryness;
    }

    public NamedAPIResource getFirmness() {
        return firmness;
    }

    public void setFirmness(NamedAPIResource firmness) {
        this.firmness = firmness;
    }

    public List<BerryFlavorMap> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<BerryFlavorMap> flavors) {
        this.flavors = flavors;
    }

    public NamedAPIResource getItem() {
        return item;
    }

    public void setItem(NamedAPIResource item) {
        this.item = item;
    }

    public NamedAPIResource getNaturalGiftType() {
        return naturalGiftType;
    }

    public void setNaturalGiftType(NamedAPIResource naturalGiftType) {
        this.naturalGiftType = naturalGiftType;
    }
}
