package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

    @InjectMocks
    ExerciseService exerciseService;

    @Mock
    ExerciseRepository exerciseRepository;

    @Test
    void shouldReturnAllExercises() {
        // GIVEN a list of exercises
        List<Exercise> exercises = createExercises();
        when(exerciseRepository.findAll()).thenReturn(exercises);

        // WHEN getting all exercises
        List<ExerciseData> exerciseDatas = exerciseService.getAllExercises();

        // THEN the exercises are sorted by name
        verify(exerciseRepository).findAll();
        assertEquals(3, exerciseDatas.size());
        assertThat(exerciseDatas.getFirst()).extracting("id", "name")
                .containsExactly(1L, "Bench Press");
        assertThat(exerciseDatas.get(1)).extracting("id", "name")
                .containsExactly(3L, "Deadlift");
        assertThat(exerciseDatas.get(2)).extracting("id", "name")
                .containsExactly(2L, "Squat");
    }

    @Test
    void shouldSaveExercise() {
        // GIVEN an exercise
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Bench Press");
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        // WHEN saving the exercise
        final ArgumentCaptor<Exercise> exerciseCaptor = ArgumentCaptor.forClass(Exercise.class);
        exerciseService.save(exercise);

        // THEN the exercise is saved
        verify(exerciseRepository).save(exerciseCaptor.capture());
        assertThat(exerciseCaptor.getValue().getId()).isEqualTo(1L);
        assertThat(exerciseCaptor.getValue().getName()).isEqualTo("Bench Press");
    }

    private List<Exercise> createExercises() {
        List<Exercise> exercises = new ArrayList<>();

        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Bench Press");
        exercises.add(exercise);

        exercise = new Exercise();
        exercise.setId(2L);
        exercise.setName("Squat");
        exercises.add(exercise);

        exercise = new Exercise();
        exercise.setId(3L);
        exercise.setName("Deadlift");
        exercises.add(exercise);

        return exercises;
    }

}