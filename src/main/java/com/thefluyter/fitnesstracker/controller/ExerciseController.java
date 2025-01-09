package com.thefluyter.fitnesstracker.controller;

import com.thefluyter.fitnesstracker.serivce.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("fitness")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("exercise")
    public ResponseEntity<List<String>> getAllExercises() {
        List<String> exerciseNames = exerciseService.getAllExercises();
        return ResponseEntity.ok(exerciseNames);
    }

    @GetMapping("exercise/{name}")
    public ResponseEntity<Long> getExerciseId(@PathVariable(value = "name") String name) {
        Long id = exerciseService.getExerciseId(name);
        return ResponseEntity.ok(id);
    }

    @PostMapping("exercise")
    public ResponseEntity<Long> createExercise(@RequestBody String name) {
        Long id = exerciseService.createExercise(name);
        return ResponseEntity.ok(id);
    }

}
