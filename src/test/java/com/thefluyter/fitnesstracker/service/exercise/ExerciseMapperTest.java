package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciseMapperTest {

    @Test
    void shouldMapToExerciseDtos() {
        // GIVEN a collection of exercises
        Exercise exercise1 = new Exercise(1L, "Bench Press");
        Exercise exercise2 = new Exercise(2L, "Barbell Curl");
        Exercise exercise3 = new Exercise(3L, "Pull-ups");

        // WHEN toExerciseDtos is called
        List<ExerciseDto> exerciseDtos = ExerciseMapper.INSTANCE.toExerciseDtos(List.of(exercise1, exercise2, exercise3));

        // THEN the ExerciseDtos are returned
        assertThat(exerciseDtos).hasSize(3);
        assertThat(exerciseDtos)
            .extracting("id")
            .containsExactly(1L, 2L, 3L);
        assertThat(exerciseDtos)
            .extracting("name")
            .containsExactly("Bench Press", "Barbell Curl", "Pull-ups");
    }

    @Test
    void shouldMapToExerciseDto() {
        // GIVEN an exercise
        Exercise exercise = new Exercise(1L, "Bench Press");

        // WHEN toExerciseDto is called
        ExerciseDto exerciseDto = ExerciseMapper.INSTANCE.toExerciseDto(exercise);

        // THEN the ExerciseDto is returned
        assertThat(exerciseDto.getId()).isEqualTo(1L);
        assertThat(exerciseDto.getName()).isEqualTo("Bench Press");
    }

    @Test
    void shouldMapToExercise() {
        // GIVEN an exerciseDto
        ExerciseDto exerciseDto = new ExerciseDto(1L, "Bench Press");

        // WHEN toExercise is called
        Exercise exercise = ExerciseMapper.INSTANCE.toExercise(exerciseDto);

        // THEN the Exercise is returned
        assertThat(exercise.getId()).isEqualTo(1L);
        assertThat(exercise.getName()).isEqualTo("Bench Press");
    }

}