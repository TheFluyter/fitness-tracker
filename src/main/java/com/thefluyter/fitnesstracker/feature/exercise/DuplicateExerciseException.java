package com.thefluyter.fitnesstracker.feature.exercise;

class DuplicateExerciseException extends RuntimeException {
    public DuplicateExerciseException(String message) {
        super(message);
    }
}
