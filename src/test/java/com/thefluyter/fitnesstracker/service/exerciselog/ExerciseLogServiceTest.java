package com.thefluyter.fitnesstracker.service.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({ExerciseLogService.class, ExerciseService.class})
class ExerciseLogServiceTest {

    @Autowired
    private ExerciseLogService exerciseLogService;

    @Test
    void testFindAll() {
        List<ExerciseLogData> logs = exerciseLogService.findAll();
        assertEquals(2, logs.size());
    }

    @Test
    void tesFindById() {
        ExerciseLogData expected = exerciseLogService.findByExerciseId(1L).getFirst();
        assertEquals("lunges", expected.getExerciseData().getName());
        assertEquals(LocalDate.of(2025, 1, 20), expected.getDate());
    }
}
