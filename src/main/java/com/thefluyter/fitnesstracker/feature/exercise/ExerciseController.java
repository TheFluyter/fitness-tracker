package com.thefluyter.fitnesstracker.feature.exercise;

import com.thefluyter.fitnesstracker.feature.exercise.exception.DuplicateExerciseException;
import com.thefluyter.fitnesstracker.shared.exercise.ExerciseDto;
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
class ExerciseController {

    private final ExerciseServiceImpl exerciseService;

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
