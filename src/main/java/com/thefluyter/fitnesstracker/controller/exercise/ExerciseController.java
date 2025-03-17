package com.thefluyter.fitnesstracker.controller.exercise;

import com.thefluyter.fitnesstracker.exception.DuplicateExerciseException;
import com.thefluyter.fitnesstracker.model.exercise.ExerciseDto;
import com.thefluyter.fitnesstracker.service.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("fitness")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public String getAllExercises(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        return "exercises";
    }

    @PostMapping("/exercises")
    public String addExercise(@ModelAttribute ExerciseDto exerciseDto, Model model) {
        try {
            exerciseService.addNewExercise(exerciseDto);
        } catch (DuplicateExerciseException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("exercises", exerciseService.getAllExercises());
            return "exercises";
        }
        return "redirect:/fitness/exercises";
    }
}
