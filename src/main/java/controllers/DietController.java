package controllers;

import model.Diet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.DietRepo;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietRepo repo;

    public DietController(DietRepo repo) {
        this.repo = repo;
    }


    // GET request (all items)
    @GetMapping
    List<Diet> getAllDiets() {
        return repo.findAll();
    }


    // GET request (one item with the given id)
    @GetMapping("/{dietId}")
    public ResponseEntity<Optional<Diet>> getOneDiet (Integer id) {
        Optional<Diet> diet = repo.findById(id);
        return diet != null ? ResponseEntity.ok(diet) : ResponseEntity.notFound().build();
    }


    // POST request
    @PostMapping
    public ResponseEntity<Void> newDietItem (@RequestBody Diet newDiet) {

        if ( repo.findById(newDiet.getId()) == null ) {  // There is no item of given ID so we can create this item.
            repo.save(newDiet);
            return ResponseEntity.status(CREATED).build();
        }
        else {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    // PUT request
    @PutMapping("/{dietId}")
    public ResponseEntity<Void> editDietItem (@RequestBody Diet newDiet) {
        if (repo.findById(newDiet.getId()) != null) {
            repo.save(newDiet);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}