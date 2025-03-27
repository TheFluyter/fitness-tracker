package com.thefluyter.fitnesstracker.service.exercise;

import com.thefluyter.fitnesstracker.exception.DuplicateExerciseException;
import com.thefluyter.fitnesstracker.exception.ExerciseNotFoundException;
import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Import({ExerciseMapper.class})
class ExerciseServiceTest {

    @InjectMocks
    ExerciseService exerciseService;

    @Mock
    ExerciseRepository exerciseRepository;

    @Captor
    private ArgumentCaptor<Exercise> exerciseCaptor;

    @Test
    void shouldReturnAllExercises() {
        // GIVEN a list of exercises
        List<Exercise> exercises = createExercises();
        when(exerciseRepository.findAll()).thenReturn(exercises);

        // WHEN getting all exercises
        List<ExerciseDto> exerciseDtos = exerciseService.getAllExercises();

        // THEN the exercises are sorted by name
        verify(exerciseRepository).findAll();
        assertEquals(3, exerciseDtos.size());
        assertThat(exerciseDtos.getFirst()).extracting("id", "name").containsExactly(1L, "Bench Press");
        assertThat(exerciseDtos.get(1)).extracting("id", "name").containsExactly(3L, "Deadlift");
        assertThat(exerciseDtos.get(2)).extracting("id", "name").containsExactly(2L, "Squat");
    }

    @Test
    void shouldAddNewExercise() {
        // GIVEN an exercise
        ExerciseDto exerciseDto = new ExerciseDto(1L, "Bench Press");
        when(exerciseRepository.findByName("Bench Press")).thenReturn(Optional.empty());

        // WHEN adding a new exercise
        exerciseService.addNewExercise(exerciseDto);

        // THEN the exercise is saved
        verify(exerciseRepository).save(exerciseCaptor.capture());
        Exercise exercise = exerciseCaptor.getValue();
        assertThat(exercise.getId()).isEqualTo(1L);
        assertThat(exercise.getName()).isEqualTo("Bench Press");
    }

    @Test
    void shouldRejectDuplicateExercise() {
        // GIVEN an exercise that already exists
        ExerciseDto exerciseDto = new ExerciseDto(1L, "Bench Press");
        when(exerciseRepository.findByName("Bench Press")).thenReturn(Optional.of(new Exercise(1L, "Bench Press")));

        // WHEN adding a new exercise
        // THEN a DuplicateExerciseException is thrown
        assertThrows(DuplicateExerciseException.class, () -> exerciseService.addNewExercise(exerciseDto));
    }

    @Test
    void shouldGetExerciseById() {
        // GIVEN an exercise
        Exercise exercise = new Exercise(1L, "Bench Press");
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        // WHEN getting an exercise by id
        ExerciseDto exerciseDto = exerciseService.findById(1L);

        // THEN the exercise is returned
        verify(exerciseRepository).findById(1L);
        assertThat(exerciseDto.getId()).isEqualTo(1L);
        assertThat(exerciseDto.getName()).isEqualTo("Bench Press");
    }

    @Test
    void shouldThrowExerciseNotFoundException() {
        // GIVEN no exercise
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());

        // WHEN getting an exercise by id
        // THEN an ExerciseNotFoundException is thrown
        assertThrows(ExerciseNotFoundException.class, () -> exerciseService.findById(1L));
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