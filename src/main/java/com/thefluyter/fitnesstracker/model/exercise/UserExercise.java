package com.thefluyter.fitnesstracker.model.exercise;

import com.thefluyter.fitnesstracker.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_exercises")
@IdClass(UserExercise.UserExerciseId.class)
@NoArgsConstructor
@AllArgsConstructor
public class UserExercise {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public static class UserExerciseId implements Serializable {

        private Long user;
        private Long exercise;

        public UserExerciseId() {}

        public UserExerciseId(Long user, Long exercise) {
            this.user = user;
            this.exercise = exercise;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserExerciseId that)) return false;
            return Objects.equals(user, that.user) &&
                Objects.equals(exercise, that.exercise);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, exercise);
        }
    }
}