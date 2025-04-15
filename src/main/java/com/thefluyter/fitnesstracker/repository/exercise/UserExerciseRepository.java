package com.thefluyter.fitnesstracker.repository.exercise;

import com.thefluyter.fitnesstracker.model.exercise.UserExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {

    @Query("SELECT COUNT(ue) > 0 FROM UserExercise ue WHERE ue.exercise.id = :exerciseId AND ue.user.id = :userId")
    boolean existsByExerciseIdAndUserId(Long exerciseId, Long userId);
}
