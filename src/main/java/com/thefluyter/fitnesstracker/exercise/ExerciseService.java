package com.thefluyter.fitnesstracker.exercise;

import com.thefluyter.fitnesstracker.user.UserExercise;
import com.thefluyter.fitnesstracker.user.UserExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUser;
import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUserId;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;

    public List<ExerciseDto> getAllExercises() {
        return ExerciseMapper.INSTANCE.toExerciseDtos(exerciseRepository.findAllForUser(getCurrentUserId())).stream()
            .sorted(Comparator.comparing(ExerciseDto::getName))
            .toList();
    }

    public void addNewExercise(ExerciseDto exerciseDto) {
        Optional<Exercise> exercise = exerciseRepository.findByName(exerciseDto.getName());
        if (exercise.isPresent()) {
            boolean userHasExercise = userExerciseRepository.existsByExerciseIdForUser(exercise.get().getId(), getCurrentUserId());
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
        Optional<Exercise> exercise = exerciseRepository.findByIdForUser(id, getCurrentUserId());
        if (exercise.isEmpty()) {
            throw new ExerciseNotFoundException("Exercise with id '%d' not found".formatted(id));
        }
        return ExerciseMapper.INSTANCE.toExerciseDto(exercise.get());
    }
}
