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

    List<ExerciseDto> exercisesToExerciseDtos(Collection<Exercise> exercises);

    ExerciseDto exerciseToExerciseDto(Exercise exercise);

    Exercise exerciseDtoToExercise(ExerciseDto exerciseDto);

}
