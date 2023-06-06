package it.unicam.cs.ids.loyaltyplatform.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class LevelController {
    private final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService)
    {
        this.levelService=levelService;
    }


    @PostMapping("/company/{companyId}/plan/{planId}/add-level")
    public ResponseEntity<String> addLevelToPlan(@PathVariable Integer companyId,
                                                 @PathVariable Integer planId,
                                                 @RequestBody LevelEntity level) {
        levelService.addLevelToPlan(companyId, planId, level);
        return ResponseEntity.status(HttpStatus.CREATED).body("level added successfully");
    }
    @DeleteMapping("/company/{companyId}/plan/{planId}/delete-level/{levelId}")
    public ResponseEntity<String> deleteLevelFromPlan(@PathVariable Integer companyId, @PathVariable Integer planId,
                                                 @PathVariable Integer levelId) {
        levelService.deleteLevel(companyId,planId,levelId);
        return ResponseEntity.status(HttpStatus.CREATED).body("level removed");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
