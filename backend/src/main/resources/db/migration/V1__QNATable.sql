CREATE TABLE questions_db
(
    id       INT PRIMARY KEY,
    question VARCHAR(200)
);

INSERT INTO questions_db (id, question)
VALUES (123456, 'What is the definition of shit??');
INSERT INTO questions_db (id, question)
VALUES (100000, 'What is JsonWebToken?');
INSERT INTO questions_db (id, question)
VALUES (111111, 'Why are you such an Idiot?');



CREATE TABLE answers_db
(
    id          INT PRIMARY KEY,
    answer      VARCHAR(1000),
    question_id INT REFERENCES questions_db (id),
    votes       INT
);

INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (123457, 'Just die already!', 123456, 42);
INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (777777, 'Stuff people do after eating stuff.', 123456, 20);

INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (1000, 'Google JWT! Thank me later.', 100000, 102);
INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (34324,
        'JSON Web Token is an Internet standard for creating data with optional signature and/or ... JWT relies on other JSON-based standards: JSON Web Signature and JSON Web Encryption',
        100000, 22);

INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (2124, 'Coz imma fukin genius', 111111, 102);
INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (2452, 'IDK, why do you ask?', 111111, 81);
INSERT INTO answers_db (id, answer, question_id, votes)
VALUES (5674, 'Because, I''m an Idiot, I guess!', 111111, 65);


SELECT question_id, question, answer
FROM questions_db
         inner join answers_db ad on questions_db.id = ad.question_id;

SELECT id, answer, votes
FROM answers_db
WHERE question_id = 123456;