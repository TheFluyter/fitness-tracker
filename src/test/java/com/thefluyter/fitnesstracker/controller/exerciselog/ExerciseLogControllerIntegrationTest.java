package com.thefluyter.fitnesstracker.controller.exerciselog;

import com.thefluyter.fitnesstracker.FitnessTrackerTest;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
        // GIVEN 10 exercise logs
        assertThat(exerciseLogRepository.findAll()).hasSize(10);
        assertThat(exerciseLogRepository.findById(1L)).isPresent();
        assertThat(exerciseLogRepository.findById(5L)).isPresent();

        // WHEN retrieving all exercise logs
        mockMvc.perform(get("/fitness/exercise-log"))
            // THEN the response should be successful and return all exercise logs
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("exerciseLogs"))
            .andExpect(model().attributeExists("exercises"))
            .andExpect(model().attribute("selectedExerciseId", is((Object) null)))
            .andExpect(model().attribute("selectedExerciseName", is((Object) null)))
            .andExpect(model().attribute("exerciseLogs", hasSize(10)))
            .andExpect(model().attribute("exerciseLogs", hasItems(
                allOf(
                    hasProperty("id", is(1L)),
                    hasProperty("date", is(LocalDate.of(2025, Month.JANUARY, 20))),
                    hasProperty("reps1", is(12)),
                    hasProperty("reps2", is(10)),
                    hasProperty("reps3", is(8)),
                    hasProperty("weight1", is(22.0)),
                    hasProperty("weight2", is(24.0)),
                    hasProperty("weight3", is(26.0))
                ),
                allOf(
                    hasProperty("id", is(5L)),
                    hasProperty("date", is(LocalDate.of(2025, Month.MARCH, 1))),
                    hasProperty("reps1", is(8)),
                    hasProperty("reps2", is(6)),
                    hasProperty("reps3", is(4)),
                    hasProperty("weight1", is(14.0)),
                    hasProperty("weight2", is(14.0)),
                    hasProperty("weight3", is(14.0))
                )
            )));
    }

    @Test
    void shouldRetrieveLogsForSpecificExercise() throws Exception {
        // GIVEN 10 exercise logs
        assertThat(exerciseLogRepository.findAll()).hasSize(10);
        assertThat(exerciseLogRepository.findById(1L)).isPresent();
        assertThat(exerciseLogRepository.findById(5L)).isPresent();

        // WHEN retrieving exercise logs for exercise with id 1
        mockMvc.perform(get("/fitness/exercise-log?exerciseId=1"))
            // THEN the response should be successful and return all exercise logs for exercise with id 1
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("exerciseLogs"))
            .andExpect(model().attributeExists("exercises"))
            .andExpect(model().attribute("selectedExerciseId", is(1L)))
            .andExpect(model().attribute("selectedExerciseName", is("lunges")))
            .andExpect(model().attribute("exerciseLogs", hasSize(2)));
    }

    @Test
    void shouldAddExerciseLog() throws Exception {
        // GIVEN two existing logs for lunges
        assertThat(exerciseLogRepository.findByExercise_Id(1L)).hasSize(2);

        ExerciseLogDto exerciseLogDto = ExerciseLogDto.builder()
            .date(LocalDate.of(2025, Month.JANUARY, 20))
            .id(null)
            .exerciseDto(new ExerciseDto(1L, "lunges"))
            .reps1(20)
            .reps2(18)
            .reps3(16)
            .weight1(22.0)
            .weight2(24.0)
            .weight3(26.0)
            .build();

        // WHEN adding a new exercise log
        mockMvc.perform(post("/fitness/exercise-log")
                .param("exerciseId", "1")
                .flashAttr("exerciseLogDto", exerciseLogDto))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fitness/exercise-log"));

        // THEN the response should be successful and the new exercise log should be added
        assertThat(exerciseLogRepository.findByExercise_Id(1L)).hasSize(3);
        List<ExerciseLog> exerciseLogs = exerciseLogRepository.findByExercise_Id(1L);
        assertThat(exerciseLogs)
            .anyMatch(log -> log.getReps1().equals(20))
            .anyMatch(log -> log.getReps2().equals(18))
            .anyMatch(log -> log.getReps3().equals(16))
            .anyMatch(log -> log.getWeight1().equals(22.0))
            .anyMatch(log -> log.getWeight2().equals(24.0))
            .anyMatch(log -> log.getWeight3().equals(26.0));
    }

}