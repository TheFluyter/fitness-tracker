package com.thefluyter.fitnesstracker.shared.exerciselog;

import com.thefluyter.fitnesstracker.shared.exercise.ExerciseDto;

import java.util.List;

public interface ExerciseLogService {

    List<ExerciseLogDto> findAll();

    List<ExerciseLogDto> findLogsByExerciseId(Long exerciseId);

    void addExerciseLog(ExerciseLogDto exerciseLogDto, ExerciseDto exerciseDto);
}
