package com.thefluyter.fitnesstracker.repository.exerciselog;

import com.thefluyter.fitnesstracker.model.exerciselog.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByExercise_Id(Long exerciseId);
}
