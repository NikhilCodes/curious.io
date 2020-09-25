CREATE TABLE users_db
(
    id       INT PRIMARY KEY    NOT NULL,
    username VARCHAR(25) UNIQUE NOT NULL,
    email    VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(64)        NOT NULL,
    role     VARCHAR(16)        NOT NULL,
    enabled  INT DEFAULT NULL
);

CREATE TABLE questions_db
(
    id       INT PRIMARY KEY,
    question VARCHAR(100),
    body     VARCHAR(2000),
    votes    INT ARRAY,
    added_on DATE,
    added_by INT
);

CREATE TABLE answers_db
(
    id          INT PRIMARY KEY,
    answer      VARCHAR(2000),
    question_id INT REFERENCES questions_db (id),
    votes       INT,
    added_on    DATE,
    added_by    INT
);