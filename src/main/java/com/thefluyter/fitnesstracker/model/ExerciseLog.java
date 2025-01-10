package com.thefluyter.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "exercise_log")
@Getter
@Setter
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_log_seq")
    @SequenceGenerator(name = "exercise_log_seq", sequenceName = "exercise_log_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private Exercise exercise;

    @Column(name = "reps1")
    private Integer reps1;

    @Column(name = "reps2")
    private Integer reps2;

    @Column(name = "reps3")
    private Integer reps3;

    @Column(name = "weight1")
    private Double weight1;

    @Column(name = "weight2")
    private Double weight2;

    @Column(name = "weight3")
    private Double weight3;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
