package com.thefluyter.fitnesstracker.controller.exerciselog;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLogDto;
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
        List<ExerciseLogDto> exerciseLogs;
        String selectedExerciseName = null;

        if (exerciseId != null) {
            exerciseLogs = exerciseLogService.findLogsByExerciseId(exerciseId);
            selectedExerciseName = exerciseService.findById(exerciseId).getName();
        } else {
            exerciseLogs = exerciseLogService.findAll();
        }

        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        model.addAttribute("exerciseLogs", exerciseLogs);
        model.addAttribute("exercises", exercises);
        model.addAttribute("selectedExerciseName", selectedExerciseName);
        model.addAttribute("selectedExerciseId", exerciseId);
        return "exercise-log";
    }

    @PostMapping("exercise-log")
    public String addExerciseLog(@ModelAttribute ExerciseLogDto exerciseLogDto, @RequestParam("exerciseId") Long exerciseId) {
        ExerciseDto exerciseDto = exerciseService.findById(exerciseId);
        exerciseLogService.addExerciseLog(exerciseLogDto, exerciseDto);
        return "redirect:/fitness/exercise-log";
    }
}
