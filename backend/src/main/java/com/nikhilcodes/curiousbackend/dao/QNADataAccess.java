package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.model.UserModel;
import com.nikhilcodes.curiousbackend.utils.RandomIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

@Repository("qna-data")
public class QNADataAccess implements QNADao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QNADataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<QNAModel> get10QNAs(int start) {
        final String query_sql = String.format("SELECT id, question, body, votes, added_on, added_by FROM questions_db ORDER BY added_on DESC OFFSET %d LIMIT 10", start);
        return jdbcTemplate.query(query_sql, (resultSet, i) -> {
            int q_id = resultSet.getInt("id");
            String question = resultSet.getString("question");
            String body = resultSet.getString("body");
            Date added_on = resultSet.getDate("added_on");
            List<Integer> votes = Arrays.asList((Integer[]) resultSet.getArray("votes").getArray());
            int addedByUserWithId = resultSet.getInt("added_by");
            return new QNAModel(
                    question,
                    body,
                    q_id,
                    jdbcTemplate.query(
                            "SELECT id, answer, up_votes, down_votes, added_on, added_by FROM answers_db WHERE question_id = ? ORDER BY added_on DESC",
                            new Object[]{q_id},
                            (answerSet, i_) -> new AnswerModel(
                                    answerSet.getString("answer"),
                                    answerSet.getInt("id"),
                                    Arrays.asList((Integer[]) answerSet.getArray("up_votes").getArray()),
                                    Arrays.asList((Integer[]) answerSet.getArray("down_votes").getArray()),
                                    answerSet.getDate("added_on"),
                                    getUserById(answerSet.getInt("added_by"))
                            )
                    ),
                    votes,
                    added_on,
                    getUserById(addedByUserWithId));
        });
    }

    @Override
    public void addQuestion(QNAModel question, int id, String email) {
        int user_id = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id FROM users_db WHERE email=?", new Object[]{email}, ((resultSet, i) -> resultSet.getInt("id")))).get();
        jdbcTemplate.update("INSERT INTO questions_db (id, question, body, votes, added_on, added_by) VALUES (?, ?, ?, ?, ?, ?)", id, question.getQuestion(), question.getBody(),
                new int[]{}, new Date(Calendar.getInstance().getTimeInMillis()), user_id);
    }

    @Override
    public Optional<QNAModel> getQNAById(int id) {
        final String query_sql = "SELECT id, question, body, votes, added_on, added_by FROM questions_db WHERE id=?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(query_sql, new Object[]{id}, ((resultSet, i) -> new QNAModel(
                        resultSet.getString("question"),
                        resultSet.getString("body"),
                        id,
                        jdbcTemplate.query(
                                "SELECT id, answer, up_votes, down_votes, added_on, added_by FROM answers_db WHERE question_id = ? ORDER BY up_votes DESC",
                                new Object[]{id},
                                (answerSet, i1) -> new AnswerModel(
                                        answerSet.getString("answer"),
                                        answerSet.getInt("id"),
                                        Arrays.asList((Integer[]) answerSet.getArray("up_votes").getArray()),
                                        Arrays.asList((Integer[]) answerSet.getArray("down_votes").getArray()),
                                        answerSet.getDate("added_on"),
                                        getUserById(answerSet.getInt("added_by"))
                                )
                        ),
                        Arrays.asList((Integer[]) resultSet.getArray("votes").getArray()),
                        resultSet.getDate("added_on"),
                        getUserById(resultSet.getInt("added_by"))))
                )
        );
    }

    @Override
    public void addAnswerToQuestionById(AnswerModel answer, int id, String email) {
        int user_id = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id FROM users_db WHERE email=?", new Object[]{email}, ((resultSet, i) -> resultSet.getInt("id")))).get();
        jdbcTemplate.update("INSERT INTO answers_db (id, answer, question_id, up_votes, down_votes, added_on, added_by) VALUES (?, ?, ?, ?, ?, ?, ?)", RandomIdGenerator.generate(), answer.getAnswer(), id, new int[]{}, new int[]{}, new Date(Calendar.getInstance().getTimeInMillis()), user_id);
    }

    @Override
    public List<Integer> toggleVote(int id, String email) {
        Integer uid = getUidFromUserEmail(email);
        List<Integer> votesCast = new ArrayList<>(Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT votes FROM questions_db", (resultSet, i) -> Arrays.asList((Integer[]) resultSet.getArray("votes").getArray()))));

        if (votesCast.contains(uid)) {
            // If vote casted: removing vote
            votesCast.remove(uid);
            jdbcTemplate.update("UPDATE questions_db SET votes=ARRAY_REMOVE(votes, ?)", uid);
        } else {
            // If no vote casted by user: then cast one
            votesCast.add(uid);
            jdbcTemplate.update("UPDATE questions_db SET votes=?", createSqlIntArray(votesCast));
        }
        return votesCast;
    }

    @Override
    public List<Integer> upVoteAnswer(int q_id, int a_id, String email) {
        Integer uid = getUidFromUserEmail(email);
        List<Integer> votesCast = new ArrayList<>(Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT up_votes FROM answers_db", (resultSet, i) -> Arrays.asList((Integer[]) resultSet.getArray("up_votes").getArray()))));
        if (votesCast.contains(uid)) {
            // If vote casted: removing vote
            votesCast.remove(uid);
            jdbcTemplate.update("UPDATE answers_db SET up_votes=ARRAY_REMOVE(up_votes, ?)", uid);
        } else {
            // If no vote casted by user: then cast one
            votesCast.add(uid);
            jdbcTemplate.update("UPDATE answers_db SET down_votes=ARRAY_REMOVE(down_votes, ?)", uid);
            jdbcTemplate.update("UPDATE answers_db SET up_votes=?", createSqlIntArray(votesCast));
        }
        return votesCast;
    }

    @Override
    public List<Integer> downVoteAnswer(int q_id, int a_id, String email) {
        Integer uid = getUidFromUserEmail(email);
        List<Integer> votesCast = new ArrayList<>(Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT down_votes FROM answers_db", (resultSet, i) -> Arrays.asList((Integer[]) resultSet.getArray("down_votes").getArray()))));
        if (votesCast.contains(uid)) {
            // If vote casted: removing vote
            votesCast.remove(uid);
            jdbcTemplate.update("UPDATE answers_db SET down_votes=ARRAY_REMOVE(down_votes, ?)", uid);
        } else {
            // If no vote casted by user: then cast one
            votesCast.add(uid);
            jdbcTemplate.update("UPDATE answers_db SET up_votes=ARRAY_REMOVE(up_votes, ?)", uid);
            jdbcTemplate.update("UPDATE answers_db SET down_votes=?", createSqlIntArray(votesCast));
        }
        return votesCast;
    }

    // Inter-utility functions below
    private UserModel getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT username, email, role FROM users_db WHERE id=?", new Object[]{id}, (resultSet, i) -> new UserModel(
                id,
                resultSet.getString("email"),
                resultSet.getString("username"),
                resultSet.getString("role")
        ));
    }

    private int getUidFromUserEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT id FROM users_db WHERE email=?", new Object[]{email}, (resultSet, i) -> resultSet.getInt("id"));
    }

    private java.sql.Array createSqlIntArray(List<Integer> list) {
        java.sql.Array intArray = null;
        try {
            intArray = jdbcTemplate.getDataSource().getConnection().createArrayOf("INT", list.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return intArray;
    }
}
