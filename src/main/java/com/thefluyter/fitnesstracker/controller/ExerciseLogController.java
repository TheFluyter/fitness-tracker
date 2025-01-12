package com.thefluyter.fitnesstracker.controller;

import com.thefluyter.fitnesstracker.model.Exercise;
import com.thefluyter.fitnesstracker.model.ExerciseLog;
import com.thefluyter.fitnesstracker.serivce.ExerciseLogService;
import com.thefluyter.fitnesstracker.serivce.ExerciseService;
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
        if (exerciseId != null) {
            log.info("Retrieving exercise logs for exercise id {}", exerciseId);
            exerciseLogs = exerciseLogService.findByExerciseId(exerciseId);
        } else {
            exerciseLogs = exerciseLogService.findAll();
        }
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exerciseLogs", exerciseLogs);
        model.addAttribute("exercises", exercises);
        return "exercise-log";
    }

    @PostMapping("exercise-log")
    public String addExerciseLog(@ModelAttribute ExerciseLog exerciseLog, @RequestParam("exerciseId") Long exerciseId) {
        Exercise exercise = exerciseService.findById(exerciseId);
        exerciseLog.setExercise(exercise);
        exerciseLogService.save(exerciseLog);
        return "redirect:/fitness/exercise-log";
    }
}
