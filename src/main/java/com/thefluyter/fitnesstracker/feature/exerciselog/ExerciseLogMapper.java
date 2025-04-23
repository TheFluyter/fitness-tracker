package com.thefluyter.fitnesstracker.feature.exerciselog;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExerciseLogMapper {

    ExerciseLogMapper INSTANCE = Mappers.getMapper(ExerciseLogMapper.class);

    @Mapping(target = "exercise", source = "exerciseDto")
    ExerciseLog toExerciseLog(ExerciseLogDto exerciseLogDto);

    List<ExerciseLogDto> toExerciseLogDtos(List<ExerciseLog> exerciseLogs);

    @Mapping(target = "exerciseDto", source = "exercise")
    ExerciseLogDto toExerciseLogDto(ExerciseLog exerciseLog);

}
