INSERT INTO exercise (id, exercise_name) VALUES (1, 'lunges');
INSERT INTO exercise (id, exercise_name) VALUES (2, 'flies');
INSERT INTO exercise (id, exercise_name) VALUES (3, 'pull-ups');
INSERT INTO exercise (id, exercise_name) VALUES (4, 'push-ups');
INSERT INTO exercise (id, exercise_name) VALUES (5, 'cable rows');
INSERT INTO exercise (id, exercise_name) VALUES (6, 'bench press');
INSERT INTO exercise (id, exercise_name) VALUES (7, 'deadlift');
INSERT INTO exercise (id, exercise_name) VALUES (8, 'squats');
INSERT INTO exercise (id, exercise_name) VALUES (9, 'lat pulldown');
INSERT INTO exercise (id, exercise_name) VALUES (10, 'military press');

alter sequence exercise_id_seq restart with (select max(id) + 1 from exercise);