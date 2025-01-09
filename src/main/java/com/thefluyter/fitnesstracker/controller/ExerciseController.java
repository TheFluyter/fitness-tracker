package com.thefluyter.fitnesstracker.controller;

import com.thefluyter.fitnesstracker.model.Exercise;
import com.thefluyter.fitnesstracker.serivce.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("fitness")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public String getAllExercises(Model model) {
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercises";
    }

    @GetMapping("/exercises/add")
    public String showAddExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "add-exercise";
    }

    @PostMapping("/exercises/add")
    public String addExercise(@ModelAttribute Exercise exercise) {
        exerciseService.save(exercise);
        return "redirect:/fitness/exercises";
    }

}
