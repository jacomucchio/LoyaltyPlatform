package it.unicam.cs.ids.loyaltyplatform.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LevelController {
    private final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService)
    {
        this.levelService=levelService;
    }


    @PostMapping("/company/plan/{planId}/add-level")
    public ResponseEntity<String> addLevelToPlan(@PathVariable Integer planId, @RequestBody LevelEntity level) {
        levelService.addLevelToPlan(planId, level);
        return ResponseEntity.status(HttpStatus.CREATED).body("level added successfully");
    }


}
