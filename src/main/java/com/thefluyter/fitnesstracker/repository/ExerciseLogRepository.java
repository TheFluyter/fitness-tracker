package com.thefluyter.fitnesstracker.repository;

import com.thefluyter.fitnesstracker.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
}
