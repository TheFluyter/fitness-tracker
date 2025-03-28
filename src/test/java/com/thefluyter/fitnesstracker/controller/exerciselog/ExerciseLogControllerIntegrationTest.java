package com.thefluyter.fitnesstracker.controller.exerciselog;

import com.thefluyter.fitnesstracker.FitnessTrackerTest;
import com.thefluyter.fitnesstracker.repository.exerciselog.ExerciseLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExerciseLogController.class})
class ExerciseLogControllerIntegrationTest extends FitnessTrackerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExerciseLogRepository exerciseLogRepository;

    @Test
    void shouldRetrieveAllExerciseLogs() throws Exception {
        assertThat(exerciseLogRepository.findAll()).hasSize(10);
        assertThat(exerciseLogRepository.findById(1L)).isPresent();
        assertThat(exerciseLogRepository.findById(5L)).isPresent();

        mockMvc.perform(get("/fitness/exercise-log"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("exerciseLogs"))
            .andExpect(model().attributeExists("exercises"))
            .andExpect(model().attribute("exerciseLogs", hasSize(10)))
            .andExpect(model().attribute("exerciseLogs", hasItems(
                allOf(
                    hasProperty("id", is(1)),
                    hasProperty("date", is(LocalDate.of(2025, Month.JANUARY, 20)))
                )
            )));
    }

}