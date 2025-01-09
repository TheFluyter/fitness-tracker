package com.thefluyter.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(RoutineExerciseKey.class)
@Table(name = "routine_exercises")
@Getter
@Setter
public class RoutineExercises {

    @Id
    @Column(name = "routine_id", nullable = false, unique = true)
    private Long routineId;

    @Id
    @Column(name = "exercise_id", nullable = false, unique = true)
    private Long exerciseId;
}
