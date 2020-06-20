package repositories;
import model.DietCalories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietCaloriesRepo extends JpaRepository<DietCalories, Integer> {

}