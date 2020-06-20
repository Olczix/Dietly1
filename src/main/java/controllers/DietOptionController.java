package controllers;

import model.DietOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.DietOptionsRepo;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/dietOptions")
public class DietOptionController {

    private final DietOptionsRepo repo;

    public DietOptionController(DietOptionsRepo repo) {
        this.repo = repo;
    }

    // GET request (all items)
    @GetMapping
    List<DietOption> getAllDietOptions() {
        return repo.findAll();
    }


    // GET request (one item with the given id)
    @GetMapping("/{dietOptionsId}")
    public ResponseEntity<Optional<DietOption>> getOneDietOption(Integer id) {
        Optional<DietOption> dietOption = repo.findById(id);
        return dietOption != null ? ResponseEntity.ok(dietOption) : ResponseEntity.notFound().build();
    }


    // POST request
    @PostMapping
    public ResponseEntity<Void> newDietOptionItem (@RequestBody DietOption newDietOption) {

        if ( repo.findById(newDietOption.getId()) == null ) {  // There is no item of given ID so we can create this item.
            repo.save(newDietOption);
            return ResponseEntity.status(CREATED).build();
        }
        else {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    // PUT request
    @PutMapping("/{dietOptionId}")
    public ResponseEntity<Void> editDietOptionItem (@RequestBody DietOption newDietOption) {
        if (repo.findById(newDietOption.getId()) != null) {
            repo.save(newDietOption);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
