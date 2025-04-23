package com.thefluyter.fitnesstracker.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);

    @Query("SELECT e FROM Exercise e JOIN UserExercise ue ON e.id = ue.exercise.id WHERE ue.user.id = :userId")
    List<Exercise> findAllForUser(@Param("userId") Long userId);

    @Query("SELECT e FROM Exercise e JOIN UserExercise ue ON e.id = ue.exercise.id WHERE e.id = :id AND ue.user.id = :userId")
    Optional<Exercise> findByIdForUser(Long id, Long userId);


}
