package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExerciseMapperImpl implements ExerciseMapper {

    @Override
    public List<ExerciseDto> exercisesToExerciseDtos(Collection<Exercise> exercises) {
        if (exercises == null) {
            return Collections.emptyList();
        }

        List<ExerciseDto> list = new ArrayList<>(exercises.size());
        for (Exercise exercise : exercises) {
            list.add(exerciseToExerciseDto(exercise));
        }

        return list;
    }

    public ExerciseDto exerciseToExerciseDto(Exercise exercise) {
        if (exercise == null) {
            return null;
        }

        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(exercise.getId());
        exerciseDto.setName(exercise.getName());

        return exerciseDto;
    }

    public Exercise exerciseDtoToExercise(ExerciseDto exerciseDto) {
        if (exerciseDto == null) {
            return null;
        }

        Exercise exercise = new Exercise();
        exercise.setId(exerciseDto.getId());
        exercise.setName(exerciseDto.getName());

        return exercise;
    }
}
