package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
import com.thefluyter.fitnesstracker.repository.exerciselog.ExerciseLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExerciseLogServiceTest {

    @Mock
    private ExerciseLogRepository exerciseLogRepository;

    @InjectMocks
    private ExerciseLogService exerciseLogService;

    @Captor
    private ArgumentCaptor<ExerciseLog> exerciseLogCaptor;

    @Test
    void shouldReturnAllExerciseLogs() {
        // GIVEN a list of exercise logs
        List<ExerciseLog> exerciseLogs = createExerciseLogs();
        when(exerciseLogRepository.findAll()).thenReturn(exerciseLogs);

        // WHEN getting all exercise logs
        List<ExerciseLogDto> logs = exerciseLogService.findAll();

        // THEN the exercise logs are returned
        assertThat(logs).hasSize(3);
        assertThat(logs).extracting("id", "exerciseDto.name", "date", "reps1", "reps2", "reps3", "weight1", "weight2", "weight3")
            .containsExactly(
                Assertions.tuple(1L, "lunges", LocalDate.of(2025, 1, 20), 10, 8, 6, 20.0, 25.0, 30.0),
                Assertions.tuple(2L, "pull-ups", LocalDate.of(2025, 1, 22), 12, 10, 8, 0.0, 5.0, 10.0),
                Assertions.tuple(3L, "bench press", LocalDate.of(2025, 1, 25), 8, 6, 4, 75.0, 80.0, 85.0)
            );
    }

    @Test
    void shouldReturnExerciseLogsByExerciseId() {
        // GIVEN a list of exercise logs
        List<ExerciseLog> exerciseLogs = createExerciseLogsForOneExercise();
        when(exerciseLogRepository.findByExercise_Id(1L)).thenReturn(exerciseLogs);

        // WHEN getting all exercise logs for one exercise
        List<ExerciseLogDto> logs = exerciseLogService.findLogsByExerciseId(1L);

        // THEN the exercise logs are returned
        assertThat(logs).hasSize(3);
        assertThat(logs).extracting("id", "exerciseDto.name", "date", "reps1", "reps2", "reps3", "weight1", "weight2", "weight3")
            .containsExactly(
                Assertions.tuple(1L, "lunges", LocalDate.of(2025, 1, 20), 10, 8, 6, 14.0, 16.0, 18.0),
                Assertions.tuple(2L, "lunges", LocalDate.of(2025, 1, 22), 10, 8, 6, 20.0, 22.0, 24.0),
                Assertions.tuple(3L, "lunges", LocalDate.of(2025, 1, 25), 10, 8, 6, 26.0, 28.0, 30.0)
            );
    }

    @Test
    void shouldAddExerciseLog() {
        // GIVEN an exercise log and exercise
        ExerciseDto exerciseDto = new ExerciseDto(1L, "lunges");
        ExerciseLogDto exerciseLogDto = ExerciseLogDto.builder()
            .id(1L)
            .exerciseDto(exerciseDto)
            .date(LocalDate.of(2025, 1, 20))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(20.0)
            .weight2(25.0)
            .weight3(30.0)
            .build();

        // WHEN adding an exercise to the log
        exerciseLogService.addExerciseLog(exerciseLogDto, exerciseDto);

        // THEN the exercise log is saved
        verify(exerciseLogRepository).save(exerciseLogCaptor.capture());
        ExerciseLog exerciseLog = exerciseLogCaptor.getValue();
        assertThat(exerciseLog.getId()).isEqualTo(1);
        assertThat(exerciseLog.getExercise().getId()).isEqualTo(1L);
        assertThat(exerciseLog.getDate()).isEqualTo(LocalDate.of(2025, 1, 20));
        assertThat(exerciseLog.getReps1()).isEqualTo(10);
        assertThat(exerciseLog.getReps2()).isEqualTo(8);
        assertThat(exerciseLog.getReps3()).isEqualTo(6);
        assertThat(exerciseLog.getWeight1()).isEqualTo(20.0);
        assertThat(exerciseLog.getWeight2()).isEqualTo(25.0);
        assertThat(exerciseLog.getWeight3()).isEqualTo(30.0);
    }

    private List<ExerciseLog> createExerciseLogs() {
        ExerciseLog exerciseLog1 = ExerciseLog.builder()
            .id(1L)
            .exercise(new Exercise(1L, "lunges"))
            .date(LocalDate.of(2025, 1, 20))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(20.0)
            .weight2(25.0)
            .weight3(30.0)
            .build();

        ExerciseLog exerciseLog2 = ExerciseLog.builder()
            .id(2L)
            .exercise(new Exercise(2L, "pull-ups"))
            .date(LocalDate.of(2025, 1, 22))
            .reps1(12)
            .reps2(10)
            .reps3(8)
            .weight1(0.0)
            .weight2(5.0)
            .weight3(10.0)
            .build();

        ExerciseLog exerciseLog3 = ExerciseLog.builder()
            .id(3L)
            .exercise(new Exercise(3L, "bench press"))
            .date(LocalDate.of(2025, 1, 25))
            .reps1(8)
            .reps2(6)
            .reps3(4)
            .weight1(75.0)
            .weight2(80.0)
            .weight3(85.0)
            .build();

        return List.of(exerciseLog1, exerciseLog2, exerciseLog3);
    }

    private List<ExerciseLog> createExerciseLogsForOneExercise() {
        Exercise lunges = new Exercise(1L, "lunges");

        ExerciseLog exerciseLog1 = ExerciseLog.builder()
            .id(1L)
            .exercise(lunges)
            .date(LocalDate.of(2025, 1, 20))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(14.0)
            .weight2(16.0)
            .weight3(18.0)
            .build();

        ExerciseLog exerciseLog2 = ExerciseLog.builder()
            .id(2L)
            .exercise(lunges)
            .date(LocalDate.of(2025, 1, 22))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(20.0)
            .weight2(22.0)
            .weight3(24.0)
            .build();

        ExerciseLog exerciseLog3 = ExerciseLog.builder()
            .id(3L)
            .exercise(lunges)
            .date(LocalDate.of(2025, 1, 25))
            .reps1(10)
            .reps2(8)
            .reps3(6)
            .weight1(26.0)
            .weight2(28.0)
            .weight3(30.0)
            .build();

        return List.of(exerciseLog1, exerciseLog2, exerciseLog3);
    }
}
