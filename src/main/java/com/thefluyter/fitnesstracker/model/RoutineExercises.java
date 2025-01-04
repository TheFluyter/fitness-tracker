package com.thefluyter.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(RoutineExerciseKey.class)
@Table(name = "exercise_routine")
@Getter
@Setter
public class RoutineExercises {

    @Id
    @Column(name = "routine_id", nullable = false, unique = true)
    private String routineId;

    @Id
    @Column(name = "exercise_id", nullable = false, unique = true)
    private String exerciseId;
}
