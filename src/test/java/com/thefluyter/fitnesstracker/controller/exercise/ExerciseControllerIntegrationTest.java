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
import static org.hamcrest.Matchers.notNullValue;
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
        // GIVEN 6 exercises for a user
        assertThat(exerciseRepository.findAllForUser(getUserId())).hasSize(6);
        assertThat(exerciseRepository.findByName("lunges").orElseThrow(IllegalStateException::new).getId()).isEqualTo(1L);
        assertThat(exerciseRepository.findByName("cable rows").orElseThrow(IllegalStateException::new).getId()).isEqualTo(5L);

        // WHEN retrieving all exercises
        mockMvc.perform(get("/fitness/exercises").with(userAuth()))
                // THEN the response should be successful and return all exercises for that user
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("exercises", hasSize(6)))
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
        // GIVEN no exercise with the name "dumbbell curls"
        // WHEN saving a new exercise
        mockMvc.perform(post("/fitness/exercises")
                .with(userAuth())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "dumbbell curls"))
            .andExpect(status().is3xxRedirection());

        // THEN the exercise should be saved
        assertThat(exerciseRepository.findByName("dumbbell curls")).isPresent();
    }

    @Test
    void shouldReturnErrorForDuplicateExercise() throws Exception {
        // GIVEN an exercise exists with the name "lunges"
        assertThat(exerciseRepository.findByName("lunges")).isPresent();

        // WHEN trying to save a duplicate exercise
        mockMvc.perform(post("/fitness/exercises")
                .with(userAuth())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "lunges"))
            // THEN the response should be a redirect to the exercise list with an error message
            .andExpect(status().isOk())
            .andExpect(model().attribute("errorMessage", notNullValue()));
    }

}