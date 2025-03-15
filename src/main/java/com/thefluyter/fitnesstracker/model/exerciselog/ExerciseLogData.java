package com.thefluyter.fitnesstracker.model.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExerciseLogData {

    private Integer id;
    private ExerciseData exerciseData;
    private Integer reps1;
    private Integer reps2;
    private Integer reps3;
    private Double weight1;
    private Double weight2;
    private Double weight3;
    private LocalDate date;
}
