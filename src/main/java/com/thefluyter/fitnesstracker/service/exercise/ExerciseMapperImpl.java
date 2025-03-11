package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExerciseMapperImpl implements ExerciseMapper {

    @Override
    public List<ExerciseData> exercisesToExerciseDatas(Collection<Exercise> exercises) {
        if (exercises == null) {
            return Collections.emptyList();
        }

        List<ExerciseData> list = new ArrayList<>(exercises.size());
        for (Exercise exercise : exercises) {
            list.add(exerciseToExerciseData(exercise));
        }

        return list;
    }

    public ExerciseData exerciseToExerciseData(Exercise exercise) {
        if (exercise == null) {
            return null;
        }

        ExerciseData exerciseData = new ExerciseData();
        exerciseData.setId(exercise.getId());
        exerciseData.setName(exercise.getName());

        return exerciseData;
    }

    public Exercise exerciseDataToExercise(ExerciseData exerciseData) {
        if (exerciseData == null) {
            return null;
        }

        Exercise exercise = new Exercise();
        exercise.setId(exerciseData.getId());
        exercise.setName(exerciseData.getName());

        return exercise;
    }
}
