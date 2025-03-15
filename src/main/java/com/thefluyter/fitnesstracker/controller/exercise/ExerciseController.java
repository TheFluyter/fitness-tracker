package com.thefluyter.fitnesstracker.controller.exercise;

import com.thefluyter.fitnesstracker.model.exercise.ExerciseData;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("fitness")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public String getAllExercises(Model model) {
        List<ExerciseData> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercises";
    }

    @PostMapping("/exercises")
    public String addExercise(@ModelAttribute ExerciseData exerciseData) {
        exerciseService.addNewExercise(exerciseData);
        return "redirect:/fitness/exercises";
    }
}
