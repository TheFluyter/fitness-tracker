package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;
import com.thefluyter.fitnesstracker.repository.exerciselog.ExerciseLogRepository;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExerciseLogService {

    private final ExerciseLogRepository exerciseLogRepository;

    public List<ExerciseLog> findAll() {
        return exerciseLogRepository.findAll();
    }

    public List<ExerciseLog> findByExerciseId(Long exerciseId) {
        return exerciseLogRepository.findByExercise_Id(exerciseId);
    }

    // TODO: is this the right place to add an exercise to an exercise log?
    public void save(ExerciseLogData exerciseLogData, ExerciseData exerciseData) {
        Exercise exercise = ExerciseMapper.INSTANCE.exerciseDataToExercise(exerciseData);
        ExerciseLog exerciseLog = ExerciseLogMapper.INSTANCE.exerciseLogDataToExerciseLog(exerciseLogData);
        exerciseLog.setExercise(exercise);

        ExerciseLog saved = exerciseLogRepository.save(exerciseLog);
        log.info("Saved exercise {}", saved);
    }
}
