package com.thefluyter.fitnesstracker.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {

    @Query("SELECT COUNT(ue) > 0 FROM UserExercise ue WHERE ue.exercise.id = :exerciseId AND ue.user.id = :userId")
    boolean existsByExerciseIdForUser(Long exerciseId, Long userId);
}
