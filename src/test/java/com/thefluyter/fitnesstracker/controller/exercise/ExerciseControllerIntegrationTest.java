package com.thefluyter.fitnesstracker.controller.exercise;

import com.thefluyter.fitnesstracker.FitnessTrackerTest;
import com.thefluyter.fitnesstracker.repository.exercise.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExerciseController.class})
class ExerciseControllerIntegrationTest extends FitnessTrackerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Test
    void shouldRetrieveAllExercises() throws Exception {
        assertThat(exerciseRepository.findByName("lunges").getId()).isEqualTo(1L);
        assertThat(exerciseRepository.findByName("cable rows").getId()).isEqualTo(5L);

        mockMvc.perform(get("/fitness/exercises"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("exercises", hasSize(10)))
                .andExpect(model().attribute("exercises", hasItems(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("lunges"))
                        ),
                        allOf(
                                hasProperty("id", is(5L)),
                                hasProperty("name", is("cable rows"))
                        )
                )));
    }

    @Test
    void shouldSaveExercise() throws Exception {
        mockMvc.perform(post("/fitness/exercises")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "dumbbell curls"))
            .andExpect(status().is3xxRedirection());

        assertThat(exerciseRepository.findByName("dumbbell curls").getId()).isEqualTo(11L);
    }

    @Test
    void shouldNotSaveDuplicateExercise() throws Exception {
        assertThat(exerciseRepository.findByName("lunges").getId()).isEqualTo(1L);

        mockMvc.perform(post("/fitness/exercises")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "lunges"))
            .andExpect(status().isBadRequest());

    }

}