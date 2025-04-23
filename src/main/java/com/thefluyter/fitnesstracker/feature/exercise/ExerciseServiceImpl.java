package com.thefluyter.fitnesstracker.feature.exercise;

import com.thefluyter.fitnesstracker.feature.exercise.exception.DuplicateExerciseException;
import com.thefluyter.fitnesstracker.feature.exercise.exception.ExerciseNotFoundException;
import com.thefluyter.fitnesstracker.feature.user.UserExercise;
import com.thefluyter.fitnesstracker.feature.user.UserExerciseRepository;
import com.thefluyter.fitnesstracker.shared.exercise.Exercise;
import com.thefluyter.fitnesstracker.shared.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.shared.exercise.ExerciseMapper;
import com.thefluyter.fitnesstracker.shared.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUser;
import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUserId;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;

    @Override
    public List<ExerciseDto> getAllExercises() {
        return ExerciseMapper.INSTANCE.toExerciseDtos(exerciseRepository.findAllForUser(getCurrentUserId())).stream()
            .sorted(Comparator.comparing(ExerciseDto::getName))
            .toList();
    }

    @Override
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

    @Override
    public ExerciseDto findById(long id) {
        Optional<Exercise> exercise = exerciseRepository.findByIdForUser(id, getCurrentUserId());
        if (exercise.isEmpty()) {
            throw new ExerciseNotFoundException("Exercise with id '%d' not found".formatted(id));
        }
        return ExerciseMapper.INSTANCE.toExerciseDto(exercise.get());
    }
}
