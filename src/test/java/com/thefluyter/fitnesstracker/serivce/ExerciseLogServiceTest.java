package com.thefluyter.fitnesstracker.serivce;

import com.thefluyter.fitnesstracker.model.ExerciseLog;
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
        List<ExerciseLog> logs = exerciseLogService.findAll();
        assertEquals(2, logs.size());
    }

    @Test
    void tesFindById() {
        ExerciseLog expected = exerciseLogService.findByExerciseId(1L).getFirst();
        assertEquals("lunges", expected.getExercise().getName());
        assertEquals(LocalDate.of(2025, 1, 20), expected.getDate());
    }
}
