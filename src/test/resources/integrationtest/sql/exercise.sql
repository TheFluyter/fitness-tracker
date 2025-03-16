INSERT INTO exercise (id, exercise_name) VALUES (1, 'lunges');
INSERT INTO exercise (id, exercise_name) VALUES (2, 'flies');

alter sequence exercise_id_seq restart with (select max(id) + 1 from exercise);