CREATE TABLE questions_db
(
    id       INT PRIMARY KEY,
    question VARCHAR(200),
    added_on DATE
);

INSERT INTO questions_db (id, question, added_on)
VALUES (123456, 'What is the definition of shit??', '2020-07-23');
INSERT INTO questions_db (id, question, added_on)
VALUES (100000, 'What is JsonWebToken?', '2020-07-29');
INSERT INTO questions_db (id, question, added_on)
VALUES (111111, 'Why are you such an Idiot?', '2020-08-01');

CREATE TABLE answers_db
(
    id          INT PRIMARY KEY,
    answer      VARCHAR(1000),
    question_id INT REFERENCES questions_db (id),
    votes       INT,
    added_on    DATE
);

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (123457, 'Just die already!', 123456, 42, '2020-7-24');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (777777, 'Stuff people do after eating stuff.', 123456, 20, '2020-07-29');

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (1000, 'Google JWT! Thank me later.', 100000, 102, '2020-08-02');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (34324,
        'JSON Web Token is an Internet standard for creating data with optional signature and/or ... JWT relies on other JSON-based standards: JSON Web Signature and JSON Web Encryption',
        100000, 22, '2020-08-02');

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (2124, 'Coz imma fukin genius', 111111, 102, '2020-08-04');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (2452, 'IDK, why do you ask?', 111111, 81, '2020-08-04');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (5674, 'Because, I''m an Idiot, I guess!', 111111, 65, '2020-08-04');
