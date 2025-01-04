package com.thefluyter.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercise_routine")
@Getter
@Setter
public class ExerciseRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "routine_name", nullable = false, unique = true)
    private String routineName;
}
