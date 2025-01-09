package com.thefluyter.fitnesstracker.serivce;

import com.thefluyter.fitnesstracker.model.Exercise;
import com.thefluyter.fitnesstracker.repository.ExerciseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Locale.ROOT;

@Component
@Slf4j
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<String> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        log.info("Found {} exercises", exercises.size());
        return exercises.stream()
            .map(Exercise::getName)
            .toList();
    }

    public Long getExerciseId(String name) {
        Exercise exercise = exerciseRepository.findByNameContaining(name);
        return exercise == null ? null : exercise.getId();
    }

    public Long createExercise(String name) {
        Exercise exercise = new Exercise(name.toLowerCase(ROOT));
        Exercise saved = exerciseRepository.save(exercise);
        log.info("Created exercise '{}' with id {}", saved.getName(), saved.getId());
        return saved.getId();
    }
}
