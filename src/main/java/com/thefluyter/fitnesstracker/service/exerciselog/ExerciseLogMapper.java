package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExerciseLogMapper {

    ExerciseLogMapper INSTANCE = Mappers.getMapper(ExerciseLogMapper.class);

    ExerciseLog exerciseLogDtoToExerciseLog(ExerciseLogDto exerciseLogDto);

    List<ExerciseLogDto> exerciseLogsToExerciseLogDtos(List<ExerciseLog> exerciseLogs);

    ExerciseLogDto exerciseLogToExerciseLogDto(ExerciseLog exerciseLog);

}
