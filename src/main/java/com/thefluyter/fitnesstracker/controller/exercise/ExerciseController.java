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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Controller()
@RequestMapping("fitness")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public String getAllExercises(Model model) {
        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercises";
    }

    @PostMapping("/exercises")
    public String addExercise(@ModelAttribute ExerciseDto exerciseDto) {
        try {
        exerciseService.addNewExercise(exerciseDto);
        } catch (DuplicateExerciseException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        }
        return "redirect:/fitness/exercises";
    }
}
