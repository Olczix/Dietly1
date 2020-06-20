package controllers;

import model.DietCalories;
import org.springframework.http.ResponseEntity;
import repositories.DietCaloriesRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dietCalories")
public class DietCaloriesController {

    private final DietCaloriesRepo repo;

    public DietCaloriesController(DietCaloriesRepo repo) {
        this.repo = repo;
    }

    // GET request (all items)
    @GetMapping
    List<DietCalories> getAllDietCalories() {
        return repo.findAll();
    }

    // GET request (one item with the given id)
    @GetMapping("/{dietCaloriesId}")
    public ResponseEntity<Optional<DietCalories>> getOneDietCalory(Integer id) {

        Optional<DietCalories> dietCalory = repo.findById(id);
        return dietCalory != null ? ResponseEntity.ok(dietCalory) : ResponseEntity.notFound().build();
    }


    // POST request
    @PostMapping
    public ResponseEntity<Void> newDietCaloriesItem (@RequestBody DietCalories newDietCalory) {

        if ( repo.findById(newDietCalory.getId()) == null ) {  // There is no item of given ID so we can create this item.
            repo.save(newDietCalory);
            return ResponseEntity.status(CREATED).build();
        }
        else {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    // PUT request
    @PutMapping("/{dietCaloriesId}")
    public ResponseEntity<Void> editDietCaloryItem (@RequestBody DietCalories newDietCalory) {
        if (repo.findById(newDietCalory.getId()) != null) {
            repo.save(newDietCalory);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}