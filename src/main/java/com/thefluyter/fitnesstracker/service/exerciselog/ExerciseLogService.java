package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
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

    public List<ExerciseLogDto> findAll() {
        return ExerciseLogMapper.INSTANCE.toExerciseLogDtos(exerciseLogRepository.findAll());
    }

    public List<ExerciseLogDto> findLogsByExerciseId(Long exerciseId) {
        return ExerciseLogMapper.INSTANCE.toExerciseLogDtos(exerciseLogRepository.findByExercise_Id(exerciseId));
    }

    public void addExerciseLog(ExerciseLogDto exerciseLogDto, ExerciseDto exerciseDto) {
        Exercise exercise = ExerciseMapper.INSTANCE.toExercise(exerciseDto);
        ExerciseLog exerciseLog = ExerciseLogMapper.INSTANCE.toExerciseLog(exerciseLogDto);
        exerciseLog.setExercise(exercise);

        ExerciseLog saved = exerciseLogRepository.save(exerciseLog);
        log.info("Saved exercise {}", saved);
    }
}
