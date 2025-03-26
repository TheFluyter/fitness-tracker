package com.thefluyter.fitnesstracker.model.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseLogDto {

    private Integer id;
    private ExerciseDto exerciseDto;
    private Integer reps1;
    private Integer reps2;
    private Integer reps3;
    private Double weight1;
    private Double weight2;
    private Double weight3;
    private LocalDate date;
}
