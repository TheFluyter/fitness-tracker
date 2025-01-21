CREATE TABLE exercise (
    id BIGINT NOT NULL PRIMARY KEY,
    exercise_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE exercise_log (
    id SERIAL PRIMARY KEY,
    exercise_id BIGINT NOT NULL,
    reps1 INTEGER,
    reps2 INTEGER,
    reps3 INTEGER,
    weight1 DOUBLE PRECISION,
    weight2 DOUBLE PRECISION,
    weight3 DOUBLE PRECISION,
    date DATE NOT NULL,
    CONSTRAINT exercise_log_exercise_id_fkey FOREIGN KEY (exercise_id) REFERENCES exercise (id)
);