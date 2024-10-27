INSERT INTO user_tb (id, name, email, password, memo)
VALUES (1, '테스트', 'test@test.com', '{bcrypt}$2a$10$B2USCgp2d4kZY.sllO9XMO.aLbHJ27H1hdavq00M5B1CbqqvUpLHC', null), -- password
       (2, 'test', 'test2@test.com', '{bcrypt}$2a$10$B2USCgp2d4kZY.sllO9XMO.aLbHJ27H1hdavq00M5B1CbqqvUpLHC', '화이팅!');

INSERT INTO workout_session_tb (id, user_id, date, start_time, end_time)
VALUES (3, 1, CURRENT_DATE, '15:00:00', '19:00:00'),
       (2, 1, CURRENT_DATE - 1, '12:00:00', '13:00:00'),
       (1, 1, CURRENT_DATE - 3, '13:00:00', '14:00:00'),
       (4, 1, CURRENT_DATE - 3, '13:00:00', '14:00:00'),
       (5, 1, CURRENT_DATE - 4, '12:00:00', '13:00:00'),
       (6, 1, CURRENT_DATE - 5, '15:00:00', '19:00:00'),
       (7, 1, CURRENT_DATE - 6, '13:00:00', '14:00:00'),
       (8, 1, CURRENT_DATE - 7, '12:00:00', '13:00:00'),
       (9, 1, CURRENT_DATE - 8, '15:00:00', '19:00:00'),
       (10, 1, CURRENT_DATE - 9, '13:00:00', '14:00:00'),
       (11, 1, CURRENT_DATE - 10, '12:00:00', '13:00:00'),
       (12, 1, CURRENT_DATE - 11, '15:00:00', '19:00:00'),
       (13, 1, CURRENT_DATE - 12, '13:00:00', '14:00:00'),
       (14, 1, CURRENT_DATE - 13, '12:00:00', '13:00:00'),
       (15, 1, CURRENT_DATE - 14, '15:00:00', '19:00:00'),
       (16, 1, CURRENT_DATE - 15, '13:00:00', '14:00:00'),
       (17, 1, CURRENT_DATE - 32, '12:00:00', '13:00:00'),
       (18, 1, CURRENT_DATE - 31, '15:00:00', '19:00:00'),
       (19, 1, CURRENT_DATE - 34, '13:00:00', '14:00:00'),
       (20, 1, CURRENT_DATE - 60, '12:00:00', '13:00:00'),
       (21, 1, CURRENT_DATE - 59, '15:00:00', '19:00:00'),
       (22, 1, CURRENT_DATE - 62, '13:00:00', '14:00:00'),
       (23, 1, CURRENT_DATE - 90, '15:00:00', '19:00:00'),
       (24, 1, CURRENT_DATE - 93, '13:00:00', '14:00:00'),
       (25, 1, CURRENT_DATE - 94, '12:00:00', '13:00:00'),
       (26, 1, CURRENT_DATE - 95, '15:00:00', '19:00:00'),
       (27, 1, CURRENT_DATE - 96, '13:00:00', '14:00:00'),
       (28, 1, CURRENT_DATE - 97, '12:00:00', '13:00:00'),
       (29, 1, CURRENT_DATE - 365, '15:00:00', '19:00:00'),
       (30, 1, CURRENT_DATE - 368, '13:00:00', '14:00:00'),
       (31, 1, CURRENT_DATE - 396, '12:00:00', '13:00:00'),
       (32, 1, CURRENT_DATE - 395, '15:00:00', '19:00:00');

INSERT INTO sport_tb (id, user_id, name)
VALUES (1, 1, '스쿼트'),
       (2, 1, '벤치 프레스'),
       (3, 1, '데드 리프트'),
       (4, 1, '오버 헤드 프레스');


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

INSERT INTO inbody_tb (id, user_id, date, weight, muscle, fat, percent_fat)
VALUES (1, 1, CURRENT_DATE - 31, 77.1, 36.6, 12.7, 16.5),
       (2, 1, CURRENT_DATE, 77.5, 37.0, 12.7, 16.4);
