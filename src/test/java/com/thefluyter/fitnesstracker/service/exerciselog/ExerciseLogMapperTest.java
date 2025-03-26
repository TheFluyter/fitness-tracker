package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciseLogMapperTest {

    @Test
    void shouldMapToExerciseLog() {
        // GIVEN a ExerciseLogDto
        ExerciseLogDto exerciseLogDto = ExerciseLogDto.builder()
            .id(1)
            .exerciseDto(new ExerciseDto(1L, "Bench Press"))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(100.0)
            .weight2(110.0)
            .weight3(120.0)
            .date(LocalDate.of(2020, 1, 1))
            .build();

        // WHEN toExerciseLog is called
        ExerciseLog exerciseLog = ExerciseLogMapper.INSTANCE.toExerciseLog(exerciseLogDto);

        // THEN the ExerciseLog is returned
        assertThat(exerciseLog.getId()).isEqualTo(1);
        assertThat(exerciseLog.getExercise().getId()).isEqualTo(1L);
        assertThat(exerciseLog.getExercise().getName()).isEqualTo("Bench Press");
        assertThat(exerciseLog.getReps1()).isEqualTo(10);
        assertThat(exerciseLog.getReps2()).isEqualTo(8);
        assertThat(exerciseLog.getReps3()).isEqualTo(6);
        assertThat(exerciseLog.getWeight1()).isEqualTo(100.0);
        assertThat(exerciseLog.getWeight2()).isEqualTo(110.0);
        assertThat(exerciseLog.getWeight3()).isEqualTo(120.0);
        assertThat(exerciseLog.getDate()).isEqualTo(LocalDate.of(2020, 1, 1));
    }

}