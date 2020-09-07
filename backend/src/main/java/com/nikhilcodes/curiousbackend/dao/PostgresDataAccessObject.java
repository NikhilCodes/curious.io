package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository("postgres")
public class PostgresDataAccessObject implements QNADao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDataAccessObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<QNAModel> get10QNAs(int start) {
        final String query_sql = String.format("SELECT id, question, body, votes, added_on FROM questions_db ORDER BY added_on DESC OFFSET %d LIMIT 10", start);
        return jdbcTemplate.query(query_sql, (resultSet, i) -> {
            int q_id = resultSet.getInt("id");
            String question = resultSet.getString("question");
            String body = resultSet.getString("body");
            Date added_on = resultSet.getDate("added_on");
            int votes = resultSet.getInt("votes");
            return new QNAModel(
                    question,
                    body,
                    q_id,
                    jdbcTemplate.query(
                            String.format("SELECT id, answer, votes, added_on FROM answers_db WHERE question_id = %d ORDER BY added_on DESC", q_id),
                            (answerSet, i_) -> new AnswerModel(
                                    answerSet.getString("answer"),
                                    answerSet.getInt("id"),
                                    answerSet.getInt("votes"),
                                    answerSet.getDate("added_on")
                            )
                    ),
                    votes,
                    added_on);
        });
    }

    @Override
    public void addQuestion(QNAModel question, int id) {
        jdbcTemplate.update("INSERT INTO questions_db (id, question, body, votes, added_on) VALUES (?, ?, ?, ?, ?)", id, question.getQuestion(), question.getBody(), 0, new Date(Calendar.getInstance().getTimeInMillis()));
    }

    @Override
    public Optional<QNAModel> getQNAById(int id) {
        final String query_sql = "SELECT id, question, body, votes, added_on FROM questions_db WHERE id=?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(query_sql, new Object[]{id}, ((resultSet, i) -> new QNAModel(
                        resultSet.getString("question"),
                        resultSet.getString("body"),
                        id,
                        jdbcTemplate.query(
                                String.format("SELECT id, answer, votes, added_on FROM answers_db WHERE question_id = %d ORDER BY votes DESC", id),
                                (answerSet, i1) -> new AnswerModel(
                                        answerSet.getString("answer"),
                                        answerSet.getInt("id"),
                                        answerSet.getInt("votes"),
                                        answerSet.getDate("added_on")
                                )
                        ),
                        resultSet.getInt("votes"),
                        resultSet.getDate("added_on")))
                )
        );
    }

    @Override
    public void addAnswerToQuestionById(int id, AnswerModel answer) {
        int upperBound = 999999999;
        int lowerBound = 1000;
        int answer_id = lowerBound + new Random().nextInt(upperBound - lowerBound);
        jdbcTemplate.update("INSERT INTO answers_db (id, answer, question_id, votes, added_on) VALUES (?, ?, ?, ?, ?)", answer_id, answer.getAnswer(), id, 0, new Date(Calendar.getInstance().getTimeInMillis()));
    }
}
