package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.exception.DuplicateExerciseException;
import com.thefluyter.fitnesstracker.exception.ExerciseNotFoundException;
import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exercise.UserExercise;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import com.thefluyter.fitnesstracker.repository.exercise.UserExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.thefluyter.fitnesstracker.config.AuthenticationUtils.getCurrentUser;
import static com.thefluyter.fitnesstracker.config.AuthenticationUtils.getCurrentUserId;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;

    public List<ExerciseDto> getAllExercises() {
        return ExerciseMapper.INSTANCE.toExerciseDtos(exerciseRepository.findAllByUserId(getCurrentUserId())).stream()
            .sorted(Comparator.comparing(ExerciseDto::getName))
            .toList();
    }

    public void addNewExercise(ExerciseDto exerciseDto) {
        Optional<Exercise> exercise = exerciseRepository.findByName(exerciseDto.getName());
        if (exercise.isPresent()) {
            boolean userHasExercise = userExerciseRepository.existsByExerciseIdAndUserId(exercise.get().getId(), getCurrentUserId());
            if (userHasExercise) {
                throw new DuplicateExerciseException("Exercise with name '%s' already exists".formatted(exerciseDto.getName()));
            } else {
                userExerciseRepository.save(new UserExercise(getCurrentUser(), exercise.get()));
            }
        } else {
            Exercise saved = exerciseRepository.save(ExerciseMapper.INSTANCE.toExercise(exerciseDto));
            userExerciseRepository.save(new UserExercise(getCurrentUser(), saved));
            log.info("Saved exercise '{}' to database", saved);
        }
    }

    public ExerciseDto findById(long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()) {
            throw new ExerciseNotFoundException("Exercise with id '%d' not found".formatted(id));
        }
        return ExerciseMapper.INSTANCE.toExerciseDto(exercise.get());
    }
}
