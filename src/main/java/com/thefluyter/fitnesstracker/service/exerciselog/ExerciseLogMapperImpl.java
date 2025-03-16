package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseLogMapperImpl implements ExerciseLogMapper {

    @Override
    public ExerciseLog exerciseLogDtoToExerciseLog(ExerciseLogDto exerciseLogDto) {
        if ( exerciseLogDto == null ) {
            return null;
        }

        ExerciseLog exerciseLog = new ExerciseLog();

        exerciseLog.setId(exerciseLogDto.getId());
        exerciseLog.setReps1(exerciseLogDto.getReps1());
        exerciseLog.setReps2(exerciseLogDto.getReps2());
        exerciseLog.setReps3(exerciseLogDto.getReps3());
        exerciseLog.setWeight1(exerciseLogDto.getWeight1());
        exerciseLog.setWeight2(exerciseLogDto.getWeight2());
        exerciseLog.setWeight3(exerciseLogDto.getWeight3());
        exerciseLog.setDate(exerciseLogDto.getDate());

        return exerciseLog;
    }

    @Override
    public List<ExerciseLogDto> exerciseLogsToExerciseLogDtos(List<ExerciseLog> exerciseLogs) {
        if (exerciseLogs == null) {
            return Collections.emptyList();
        }

        List<ExerciseLogDto> exerciseLogDtos = new ArrayList<>();

        for (ExerciseLog exerciseLog : exerciseLogs) {
            exerciseLogDtos.add(exerciseLogToExerciseLogDto(exerciseLog));
        }

        return exerciseLogDtos;
    }

    @Override
    public ExerciseLogDto exerciseLogToExerciseLogDto(ExerciseLog exerciseLog) {
        if (exerciseLog == null) {
            return null;
        }

        ExerciseLogDto exerciseLogDto = new ExerciseLogDto();

        exerciseLogDto.setId(exerciseLog.getId());
        exerciseLogDto.setExerciseDto(ExerciseMapper.INSTANCE.exerciseToExerciseDto(exerciseLog.getExercise()));
        exerciseLogDto.setReps1(exerciseLog.getReps1());
        exerciseLogDto.setReps2(exerciseLog.getReps2());
        exerciseLogDto.setReps3(exerciseLog.getReps3());
        exerciseLogDto.setWeight1(exerciseLog.getWeight1());
        exerciseLogDto.setWeight2(exerciseLog.getWeight2());
        exerciseLogDto.setWeight3(exerciseLog.getWeight3());
        exerciseLogDto.setDate(exerciseLog.getDate());

        return exerciseLogDto;
    }


}
