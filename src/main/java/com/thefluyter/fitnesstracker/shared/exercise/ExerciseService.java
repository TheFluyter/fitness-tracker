package com.thefluyter.fitnesstracker.shared.exercise;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getAllExercises();
    void addNewExercise(ExerciseDto exerciseDto);
    ExerciseDto findById(long id);
}
