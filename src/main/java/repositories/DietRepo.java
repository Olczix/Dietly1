package repositories;

import model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepo extends JpaRepository<Diet, Integer> {
}
