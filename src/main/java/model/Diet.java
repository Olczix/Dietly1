package model;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;

@Data
public class Diet { //typ diety, np. wegetariańska, sportowa
    private Integer dietId;
    private String name;
    private String description;

    @JsonManagedReference
    private Set<DietOption> dietOptions;

    public Integer getId() { return dietId; }

}