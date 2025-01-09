package com.thefluyter.fitnesstracker.repository;

import com.thefluyter.fitnesstracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findByNameContaining(String name);
}
