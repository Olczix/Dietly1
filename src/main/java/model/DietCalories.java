package model;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
public class DietCalories { //kaloryczność, np. 1500, 2000 kcal
    private Integer dietCaloriesId;
    private Integer calories;

    @JsonBackReference
    private DietOption dietOption;

    public Integer getId() { return dietCaloriesId; }
}