create table exercise
(
    id            bigint auto_increment not null
        primary key,
    exercise_name varchar(255)           not null
        unique
);

create table exercise_log
(
    id          bigint auto_increment not null
        primary key,
    exercise_id bigint not null
        references exercise,
    reps1       integer,
    reps2       integer,
    reps3       integer,
    weight1     double precision,
    weight2     double precision,
    weight3     double precision,
    date        date   not null
);

create table exercise_routine
(
    id           bigint auto_increment not null
        primary key,
    routine_name varchar(255)            not null
);

create table routine_exercises
(
    routine_id  bigint not null
        references exercise_routine,
    exercise_id bigint not null
        references exercise,
    primary key (routine_id, exercise_id)
);

create table users
(
    id       bigint auto_increment
        primary key,
    password varchar(255) not null,
    username varchar(255) not null
        unique
);