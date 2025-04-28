package com.thefluyter.fitnesstracker.shared.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, Long> {
}
