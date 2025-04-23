package com.thefluyter.fitnesstracker.exerciselog;

import com.thefluyter.fitnesstracker.exercise.Exercise;
import com.thefluyter.fitnesstracker.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.user.UserExerciseLog;
import com.thefluyter.fitnesstracker.user.UserExerciseLogRepository;
import com.thefluyter.fitnesstracker.exercise.ExerciseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUser;
import static com.thefluyter.fitnesstracker.security.AuthenticationUtils.getCurrentUserId;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExerciseLogService {

    private final ExerciseLogRepository exerciseLogRepository;
    private final UserExerciseLogRepository userExerciseLogRepository;

    public List<ExerciseLogDto> findAll() {
        return ExerciseLogMapper.INSTANCE.toExerciseLogDtos(exerciseLogRepository.findAllForUser(getCurrentUserId()));
    }

    public List<ExerciseLogDto> findLogsByExerciseId(Long exerciseId) {
        return ExerciseLogMapper.INSTANCE.toExerciseLogDtos(exerciseLogRepository.findByExerciseIdForUser(exerciseId, getCurrentUserId()));
    }

    public void addExerciseLog(ExerciseLogDto exerciseLogDto, ExerciseDto exerciseDto) {
        Exercise exercise = ExerciseMapper.INSTANCE.toExercise(exerciseDto);
        ExerciseLog exerciseLog = ExerciseLogMapper.INSTANCE.toExerciseLog(exerciseLogDto);
        exerciseLog.setExercise(exercise);

        ExerciseLog saved = exerciseLogRepository.save(exerciseLog);
        userExerciseLogRepository.save(new UserExerciseLog(getCurrentUser(), saved));
        log.info("Saved exercise log: {}", saved);
    }
}
