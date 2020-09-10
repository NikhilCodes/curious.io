CREATE TABLE users_db
(
    id       INT PRIMARY KEY NOT NULL ,
    username VARCHAR(25) UNIQUE NOT NULL,
    email    VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    role     VARCHAR(16) NOT NULL ,
    enabled  INT DEFAULT NULL
);

INSERT INTO users_db (id, username, email, password, role, enabled)
VALUES (424242, '1carus42', 'admin@nixel.com', '$2a$10$0gr7INy2Vh9ElLle3mExy.Z1wKpkkwbRme.OmSossvYn1fr4lPUhW', 'USER', 1);

INSERT INTO users_db (id, username, email, password, role, enabled)
VALUES (999999, 'HeathCl!ff', 'nikhil.nixel@gmail.com', '$2a$10$YVtlaS89K0KVenSQOwQsm.ZTkZ4lLUsSw99AyhLnOYkP3TgqS9Y9K', 'ADMIN', 1);


CREATE TABLE questions_db
(
    id       INT PRIMARY KEY,
    question VARCHAR(100),
    body     VARCHAR(2000),
    votes    INT,
    added_on DATE
);

INSERT INTO questions_db (id, question, body, votes, added_on)
VALUES (123456, 'Discord.py embed colour changing ', 'I have been working on a discord bot that web scrapes.I was wondering how I could change the colour of an embed depending on the variable, i have tried this before but it would not change the colour

  embed2 = discord.Embed(title=username,description=online, color=0xf21f18)
        embed2.add_field(name="profile", value= links, inline=False)
        embed = discord.Embed(title=username,description=online, color=0x1adb64)
        embed.add_field(name="profile", value= links, inline=False)
        if online == "offline" or "Offline":
            await ctx.send(embed=embed2)

        else:
            await ctx.send(embed=embed)', 23, '2020-07-23');
INSERT INTO questions_db (id, question, body, votes, added_on)
VALUES (100000, 'JWT (JSON Web Token) automatic prolongation of expiration', 'I would like to implement JWT-based authentication to our new REST API. But since the expiration is set in the token, is it possible to automatically prolong it? I don''t want users to need to sign in after every X minutes if they were actively using the application in that period. That would be a huge UX fail.

But prolonging the expiration creates a new token (and the old one is still valid until it expires). And generating a new token after each request sounds silly to me. Sounds like a security issue when more than one token is valid at the same time. Of course I could invalidate the old used one using a blacklist but I would need to store the tokens. And one of the benefits of JWT is no storage.

I found how Auth0 solved it. They use not only JWT token but also a refresh token: https://docs.auth0.com/refresh-token

But again, to implement this (without Auth0) I''d need to store refresh tokens and maintain their expiration. What is the real benefit then? Why not have only one token (not JWT) and keep the expiration on the server?

Are there other options? Is using JWT not suited for this scenario?', 56, '2020-07-29');
INSERT INTO questions_db (id, question, body, votes, added_on)
VALUES (111111, 'Firebase: deleting all the authenticated users', 'I am new to firebase and I wanted to create 10000 user with a script and I got blocked.

Now I want to delete them all and do it again but deleting it is a big problem now.

How can I delete all of these users? and make that 10000 users?', 13, '2020-08-01');

CREATE TABLE answers_db
(
    id          INT PRIMARY KEY,
    answer      VARCHAR(2000),
    question_id INT REFERENCES questions_db (id),
    votes       INT,
    added_on    DATE
);

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (123457, 'Just die already!', 123456, 42, '2020-7-24');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (777777, 'embed = discord.Embed(color=0xf21f18 if online.lower() == ''offline'' else 0x1adb64)
await ctx.send(embed=embed)', 123456, 20, '2020-07-29');

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (1000, 'Google JWT! Thank me later.', 100000, 102, '2020-08-02');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (34324,
        'JSON Web Token is an Internet standard for creating data with optional signature and/or ... JWT relies on other JSON-based standards: JSON Web Signature and JSON Web Encryption',
        100000, 22, '2020-08-02');

INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (2124, 'The easiest way to bulk delete users is likely through the Admin SDK which has an API to list users, and then to delete a user.', 111111, 102, '2020-08-04');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (2452, 'Since Node.js firebase-admin version 8.12.0, delete multiple users is supported.

Here is the sample code.

admin.auth().deleteUsers([uid1, uid2, uid3])
  .then(deleteUsersResult => {
    console.log(''Successfully deleted '' + deleteUsersResult.successCount + '' users'');
    console.log(''Failed to delete '' +  deleteUsersResult.failureCount + '' users'');
    deleteUsersResult.errors.forEach(err => {
      console.log(err.error.toJSON());
    });
  })
  .catch(error => {
    console.log(''Error deleting users:'', error);
  });
Notice: the maximum number of users allowed to be deleted is 1000 per batch like list all users', 111111, 81, '2020-08-04');
INSERT INTO answers_db (id, answer, question_id, votes, added_on)
VALUES (5674, 'Easier to do than you might think at first glance of the documentation.

A few lines will do it in Python:

import firebase_admin
from firebase_admin import credentials
from firebase_admin import auth

cred = credentials.Certificate("/path/to/downloaded/json/key/*.json")
firebase_admin.initialize_app(cred)

for user in auth.list_users().iterate_all():
    print("Deleting user " + user.uid)
    auth.delete_user(user.uid)', 111111, 65, '2020-08-04');
