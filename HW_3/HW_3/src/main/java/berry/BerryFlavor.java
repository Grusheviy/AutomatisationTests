package berry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "berries",
        "contest_type",
        "names"
})
public class BerryFlavor {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("berries")
    private List<FlavorBerryMap> berries = null;
    @JsonProperty("contest_type")
    private NamedAPIResource contestType;
    @JsonProperty("names")
    private List<Name> names = null;

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

    public List<FlavorBerryMap> getBerries() {
        return berries;
    }

    public void setBerries(List<FlavorBerryMap> berries) {
        this.berries = berries;
    }

    public NamedAPIResource getContestType() {
        return contestType;
    }

    public void setContestType(NamedAPIResource contestType) {
        this.contestType = contestType;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }
}

