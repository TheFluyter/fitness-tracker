package com.thefluyter.fitnesstracker.serivce;

import com.thefluyter.fitnesstracker.model.Exercise;
import com.thefluyter.fitnesstracker.repository.ExerciseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream()
            .sorted(Comparator.comparing(Exercise::getName))
            .toList();
    }

    public void save(Exercise exercise) {
        Exercise saved = exerciseRepository.save(exercise);
        log.info("Saved exercise {}", saved);
    }
}
