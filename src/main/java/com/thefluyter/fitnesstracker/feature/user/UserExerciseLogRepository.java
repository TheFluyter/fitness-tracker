package com.thefluyter.fitnesstracker.feature.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, Long> {
}
