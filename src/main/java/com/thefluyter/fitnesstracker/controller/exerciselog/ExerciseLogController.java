package com.thefluyter.fitnesstracker.controller.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogData;
import com.thefluyter.fitnesstracker.service.exerciselog.ExerciseLogService;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("fitness")
@RequiredArgsConstructor
@Slf4j
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;
    private final ExerciseService exerciseService;

    @GetMapping("exercise-log")
    public String getExerciseLogs(@RequestParam(value = "exerciseId", required = false) Long exerciseId, Model model) {
        List<ExerciseLog> exerciseLogs;
        String selectedExerciseName = null;

        if (exerciseId != null) {
            exerciseLogs = exerciseLogService.findByExerciseId(exerciseId);
            selectedExerciseName = exerciseService.findById(exerciseId).getName();
        } else {
            exerciseLogs = exerciseLogService.findAll();
        }

        List<ExerciseData> exercises = exerciseService.getAllExercises();
        model.addAttribute("exerciseLogs", exerciseLogs);
        model.addAttribute("exercises", exercises);
        model.addAttribute("selectedExerciseName", selectedExerciseName);
        model.addAttribute("selectedExerciseId", exerciseId);
        return "exercise-log";
    }

    @PostMapping("exercise-log")
    public String addExerciseLog(@ModelAttribute ExerciseLogData exerciseLogData, @RequestParam("exerciseId") Long exerciseId) {
        ExerciseData exerciseData = exerciseService.findById(exerciseId);
        exerciseLogService.save(exerciseLogData, exerciseData);
        return "redirect:/fitness/exercise-log";
    }
}
