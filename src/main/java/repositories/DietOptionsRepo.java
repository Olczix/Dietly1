package repositories;

import model.DietOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietOptionsRepo extends JpaRepository<DietOption, Integer> {
}
