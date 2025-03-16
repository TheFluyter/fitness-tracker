package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
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

    public List<ExerciseDto> getAllExercises() {
        return ExerciseMapper.INSTANCE.exercisesToExerciseDtos(exerciseRepository.findAll()).stream()
            .sorted(Comparator.comparing(ExerciseDto::getName))
            .toList();
    }

    public void addNewExercise(ExerciseDto exerciseDto) {
        Exercise saved = exerciseRepository.save(ExerciseMapper.INSTANCE.exerciseDtoToExercise(exerciseDto));
        log.info("Saved exercise '{}'", saved);
    }

    public ExerciseDto findById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        return ExerciseMapper.INSTANCE.exerciseToExerciseDto(exercise);
    }
}
