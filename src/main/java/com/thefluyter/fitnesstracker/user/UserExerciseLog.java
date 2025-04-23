package com.thefluyter.fitnesstracker.user;

import com.thefluyter.fitnesstracker.exerciselog.ExerciseLog;
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
@Table(name = "user_exercise_logs")
@IdClass(UserExerciseLog.UserExerciseLogId.class)
@NoArgsConstructor
@AllArgsConstructor
public class UserExerciseLog {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "exercise_log_id")
    private ExerciseLog exerciseLog;

    public static class UserExerciseLogId implements Serializable {

        private Long user;
        private Long exerciseLog;

        public UserExerciseLogId() {}

        public UserExerciseLogId(Long user, Long exerciseLog) {
            this.user = user;
            this.exerciseLog = exerciseLog;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserExerciseLogId that)) return false;
            return Objects.equals(user, that.user) &&
                Objects.equals(exerciseLog, that.exerciseLog);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, exerciseLog);
        }
    }
}
