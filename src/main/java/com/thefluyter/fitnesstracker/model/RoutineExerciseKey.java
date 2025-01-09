package com.thefluyter.fitnesstracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoutineExerciseKey implements Serializable {

    private Long routineId;
    private Long exerciseId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RoutineExerciseKey other = (RoutineExerciseKey) object;
        return Objects.equals(routineId, other.routineId) &&
            Objects.equals(exerciseId, other.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineId, exerciseId);
    }

}
