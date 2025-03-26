package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ExerciseMapper {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    List<ExerciseDto> toExerciseDtos(Collection<Exercise> exercises);

    ExerciseDto toExerciseDto(Exercise exercise);

    Exercise toExercise(ExerciseDto exerciseDto);

}
