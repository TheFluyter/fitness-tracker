package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciseLogMapperTest {

    @Test
    void shouldMapToExerciseLog() {
        // GIVEN an ExerciseLogDto
        ExerciseLogDto exerciseLogDto = ExerciseLogDto.builder()
            .id(1L)
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

    @Test
    void shouldMapToExerciseDtos() {
        // GIVEN a list of Exercises
        ExerciseLog exercise1 = ExerciseLog.builder()
            .id(1L)
            .exercise(new Exercise(1L, "Bench Press"))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(100.0)
            .weight2(110.0)
            .weight3(120.0)
            .date(LocalDate.of(2020, 1, 1))
            .build();

        ExerciseLog exercise2 = ExerciseLog.builder()
            .id(2L)
            .exercise(new Exercise(2L, "Barbell Squats"))
            .reps1(5)
            .reps2(4)
            .reps3(3)
            .weight1(150.0)
            .weight2(160.0)
            .weight3(170.0)
            .date(LocalDate.of(2020, 1, 20))
            .build();

        // WHEN toExerciseLogDtos is called
        List<ExerciseLogDto> exerciseLogDtos = ExerciseLogMapper.INSTANCE.toExerciseLogDtos(List.of(exercise1, exercise2));

        // THEN the ExerciseLogDtos is returned
        assertThat(exerciseLogDtos).hasSize(2);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getId).containsExactlyInAnyOrder(1L, 2L);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getDate).containsExactlyInAnyOrder(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 20));
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getReps1).containsExactlyInAnyOrder(10, 5);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getReps2).containsExactlyInAnyOrder(8, 4);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getReps3).containsExactlyInAnyOrder(6, 3);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getWeight1).containsExactlyInAnyOrder(100.0, 150.0);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getWeight2).containsExactlyInAnyOrder(110.0, 160.0);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getWeight3).containsExactlyInAnyOrder(120.0, 170.0);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getExerciseDto).extracting(ExerciseDto::getId).containsExactlyInAnyOrder(1L, 2L);
        assertThat(exerciseLogDtos).extracting(ExerciseLogDto::getExerciseDto).extracting(ExerciseDto::getName).containsExactlyInAnyOrder("Bench Press", "Barbell Squats");
    }

    @Test
    void shouldMapToExerciseLogDto() {
        // GIVEN an ExerciseLog
        ExerciseLog exerciseLog = ExerciseLog.builder()
            .id(1L)
            .exercise(new Exercise(1L, "Bench Press"))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(100.0)
            .weight2(110.0)
            .weight3(120.0)
            .date(LocalDate.of(2020, 1, 1))
            .build();

        // WHEN toExerciseLogDto is called
        ExerciseLogDto exerciseLogDto = ExerciseLogMapper.INSTANCE.toExerciseLogDto(exerciseLog);

        // THEN the ExerciseLogDto is returned
        assertThat(exerciseLogDto.getId()).isEqualTo(1);
        assertThat(exerciseLogDto.getExerciseDto().getId()).isEqualTo(1L);
        assertThat(exerciseLogDto.getExerciseDto().getName()).isEqualTo("Bench Press");
        assertThat(exerciseLogDto.getReps1()).isEqualTo(10);
        assertThat(exerciseLogDto.getReps2()).isEqualTo(8);
        assertThat(exerciseLogDto.getReps3()).isEqualTo(6);
        assertThat(exerciseLogDto.getWeight1()).isEqualTo(100.0);
        assertThat(exerciseLogDto.getWeight2()).isEqualTo(110.0);
        assertThat(exerciseLogDto.getWeight3()).isEqualTo(120.0);
        assertThat(exerciseLogDto.getDate()).isEqualTo(LocalDate.of(2020, 1, 1));
    }

}