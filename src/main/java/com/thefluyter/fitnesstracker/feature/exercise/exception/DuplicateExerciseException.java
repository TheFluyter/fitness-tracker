package com.thefluyter.fitnesstracker.feature.exercise.exception;

public class DuplicateExerciseException extends RuntimeException {
    public DuplicateExerciseException(String message) {
        super(message);
    }
}
