INSERT INTO user_tb (id, name, email, password)
VALUES (1, '안혜준', 'yoy06056@pusan.ac.kr', '{bcrypt}$2a$10$B2USCgp2d4kZY.sllO9XMO.aLbHJ27H1hdavq00M5B1CbqqvUpLHC'), -- password
       (2, '자갈돌', 'jagaldol.dev@gmail.com', '{bcrypt}$2a$10$B2USCgp2d4kZY.sllO9XMO.aLbHJ27H1hdavq00M5B1CbqqvUpLHC');

INSERT INTO sport_tb (id, user_id, name)
VALUES (1, 1, '스쿼트'),
       (2, 1, '벤치 프레스'),
       (3, 1, '데드 리프트'),
       (4, 1, '오버 헤드 프레스');

INSERT INTO workout_session_tb (id, user_id, date, start_time, end_time)
VALUES (1, 1, '2024-04-13', '13:00:00', '14:00:00'),
       (2, 1, '2024-04-15', '12:00:00', '13:00:00'),
       (3, 1, '2024-04-16', '15:00:00', '19:00:00');

INSERT INTO workout_record_tb (id, workout_session_id, sport_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 3),
       (4, 3, 2),
       (5, 3, 4);

INSERT INTO set_record_tb (id, workout_record_id, weight, count)
VALUES (1, 1, 60, 5),
       (2, 1, 70, 5),
       (3, 1, 70, 5),
       (4, 1, 70, 5),
       (5, 1, 70, 5),
       (6, 1, 70, 3),
       (7, 2, 40, 10),
       (8, 2, 55, 10),
       (9, 2, 55, 10),
       (10, 2, 55, 10),
       (11, 3, 80, 3),
       (12, 3, 100, 5),
       (13, 3, 100, 5),
       (14, 3, 100, 5),
       (15, 4, 55, 10),
       (16, 4, 55, 10),
       (17, 4, 55, 10),
       (18, 4, 55, 10),
       (19, 4, 55, 8);