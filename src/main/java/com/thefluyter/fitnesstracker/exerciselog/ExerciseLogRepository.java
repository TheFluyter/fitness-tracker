package com.thefluyter.fitnesstracker.exerciselog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {

    @Query("SELECT el FROM ExerciseLog el JOIN UserExerciseLog uel ON el.id = uel.exerciseLog.id WHERE uel.user.id = :userId")
    List<ExerciseLog> findAllForUser(Long userId);

    @Query("SELECT el FROM ExerciseLog el JOIN UserExerciseLog uel ON el.id = uel.exerciseLog.id WHERE uel.user.id = :userId AND el.exercise.id = :exerciseId")
    List<ExerciseLog> findByExerciseIdForUser(Long exerciseId, Long userId);
}
