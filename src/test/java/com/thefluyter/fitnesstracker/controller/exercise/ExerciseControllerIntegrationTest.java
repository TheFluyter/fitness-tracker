package com.thefluyter.fitnesstracker.controller.exercise;

import com.thefluyter.fitnesstracker.FitnessTrackerTest;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExerciseController.class})
class ExerciseControllerIntegrationTest extends FitnessTrackerTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Test
    void test() {
        assertThat(exerciseRepository.findByNameContaining("lunges").getId()).isEqualTo(1L);
    }

}