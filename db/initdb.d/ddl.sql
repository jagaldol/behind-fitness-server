CREATE TABLE user_tb
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(20)  NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    memo       VARCHAR(100),
    gender     BOOLEAN   DEFAULT FALSE,
    height     DOUBLE    DEFAULT 170,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE sport_tb
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    name       VARCHAR(20),
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (user_id, name)
);

CREATE TABLE workout_session_tb
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    date       DATE   NOT NULL,
    start_time TIME,
    end_time   TIME,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE workout_record_tb
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    workout_session_id BIGINT NOT NULL,
    sport_id           BIGINT NOT NULL,
    created_at         TIMESTAMP DEFAULT now()
);

CREATE TABLE set_record_tb
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    workout_record_id BIGINT NOT NULL,
    weight            DOUBLE     DEFAULT 0,
    count             INT        DEFAULT 0,
    count_unit        VARCHAR(5) DEFAULT '회',
    created_at        TIMESTAMP  DEFAULT now()
);

CREATE TABLE inbody_tb
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT    NOT NULL,
    date        DATE      NOT NULL,
    weight      DOUBLE    NULL DEFAULT 0,
    muscle      DOUBLE    NULL DEFAULT 0,
    fat         DOUBLE    NULL DEFAULT 0,
    percent_fat DOUBLE    NULL DEFAULT 0,
    created_at  TIMESTAMP NULL DEFAULT now()
);