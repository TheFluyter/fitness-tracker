create table user_exercises
(
    user_id     bigint not null
        references users,
    exercise_id bigint not null
        references exercise,
    primary key (user_id, exercise_id)
);

create table user_exercise_logs
(
    user_id     bigint not null
        references users,
    exercise_log_id bigint not null
        references exercise_log,
    primary key (user_id, exercise_log_id)
);
