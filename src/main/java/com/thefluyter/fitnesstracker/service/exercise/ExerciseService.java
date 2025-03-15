package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseData> getAllExercises() {
        return ExerciseMapper.INSTANCE.exercisesToExerciseDatas(exerciseRepository.findAll()).stream()
            .sorted(Comparator.comparing(ExerciseData::getName))
            .toList();
    }

    public void addNewExercise(ExerciseData exerciseData) {
        Exercise saved = exerciseRepository.save(ExerciseMapper.INSTANCE.exerciseDataToExercise(exerciseData));
        log.info("Saved exercise '{}'", saved);
    }

    public ExerciseData findById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        return ExerciseMapper.INSTANCE.exerciseToExerciseData(exercise);
    }
}
