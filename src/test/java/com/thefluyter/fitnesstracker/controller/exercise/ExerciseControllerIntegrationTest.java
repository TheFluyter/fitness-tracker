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
        assertThat(exerciseRepository.findByNameContaining("lunges").getId()).isEqualTo(1L);
        assertThat(exerciseRepository.findByNameContaining("flies").getId()).isEqualTo(2L);

        mockMvc.perform(get("/fitness/exercises"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("exercises", hasSize(2)))
                .andExpect(model().attribute("exercises", hasItems(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("lunges"))
                        ),
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("flies"))
                        )
                )));
    }

    @Test
    void shouldSaveExercise() throws Exception {
        assertThat(exerciseRepository.findByNameContaining("lunges").getId()).isEqualTo(1L);
        assertThat(exerciseRepository.findByNameContaining("flies").getId()).isEqualTo(2L);

        mockMvc.perform(post("/fitness/exercises")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("name", "pullups"));
    }

}