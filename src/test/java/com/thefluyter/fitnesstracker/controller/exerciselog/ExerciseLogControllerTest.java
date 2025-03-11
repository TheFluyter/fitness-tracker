package com.thefluyter.fitnesstracker.controller.exerciselog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ExerciseLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllExercises() throws Exception {
        mockMvc.perform(get("/fitness/exercise-log"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("exerciseLogs"))
            .andExpect(model().attributeExists("exercises"))
            .andExpect(view().name("exercise-log"));
    }

    @Test
    void testGetOneExercise() throws Exception {
        mockMvc.perform(get("/fitness/exercise-log?exerciseId=1"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("exerciseLogs"))
            .andExpect(model().attributeExists("exercises"))
            .andExpect(model().attributeExists("selectedExerciseName"))
            .andExpect(model().attributeExists("selectedExerciseId"))
            .andExpect(view().name("exercise-log"));
    }
}
