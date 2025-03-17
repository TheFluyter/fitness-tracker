package com.thefluyter.fitnesstracker.repository.exercise;

import com.thefluyter.fitnesstracker.model.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findByName(String name);
}
