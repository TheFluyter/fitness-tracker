package com.thefluyter.fitnesstracker.repository;

import com.thefluyter.fitnesstracker.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByExercise_Id(Long exerciseId);
}
