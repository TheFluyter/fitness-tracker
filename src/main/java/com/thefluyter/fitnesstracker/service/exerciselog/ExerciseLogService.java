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
        return ExerciseLogMapper.INSTANCE.exerciseLogsToExerciseLogDtos(exerciseLogRepository.findAll());
    }

    public List<ExerciseLogDto> findByExerciseLogById(Long exerciseId) {
        return ExerciseLogMapper.INSTANCE.exerciseLogsToExerciseLogDtos(exerciseLogRepository.findByExercise_Id(exerciseId));
    }

    public void addExerciseToLog(ExerciseLogDto exerciseLogDto, ExerciseDto exerciseDto) {
        Exercise exercise = ExerciseMapper.INSTANCE.exerciseDtoToExercise(exerciseDto);
        ExerciseLog exerciseLog = ExerciseLogMapper.INSTANCE.exerciseLogDtoToExerciseLog(exerciseLogDto);
        exerciseLog.setExercise(exercise);

        ExerciseLog saved = exerciseLogRepository.save(exerciseLog);
        log.info("Saved exercise {}", saved);
    }
}
