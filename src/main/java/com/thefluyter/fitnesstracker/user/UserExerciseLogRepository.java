package com.thefluyter.fitnesstracker.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, Long> {
}
