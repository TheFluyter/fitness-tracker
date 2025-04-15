package com.thefluyter.fitnesstracker.repository.exercise;

import com.thefluyter.fitnesstracker.model.exerciselog.UserExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, Long> {
}
