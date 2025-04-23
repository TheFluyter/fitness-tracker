package com.thefluyter.fitnesstracker.user;

import com.thefluyter.fitnesstracker.exerciselog.UserExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, Long> {
}
