package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
            int votes = resultSet.getInt("votes");
            int addedByUserWithId = resultSet.getInt("added_by");
            return new QNAModel(
                    question,
                    body,
                    q_id,
                    jdbcTemplate.query(
                            String.format("SELECT id, answer, votes, added_on, added_by FROM answers_db WHERE question_id = %d ORDER BY added_on DESC", q_id),
                            (answerSet, i_) -> new AnswerModel(
                                    answerSet.getString("answer"),
                                    answerSet.getInt("id"),
                                    answerSet.getInt("votes"),
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
        jdbcTemplate.update("INSERT INTO questions_db (id, question, body, votes, added_on, added_by) VALUES (?, ?, ?, ?, ?, ?)", id, question.getQuestion(), question.getBody(), 0, new Date(Calendar.getInstance().getTimeInMillis()), user_id);
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
                                String.format("SELECT id, answer, votes, added_on, added_by FROM answers_db WHERE question_id = %d ORDER BY votes DESC", id),
                                (answerSet, i1) -> new AnswerModel(
                                        answerSet.getString("answer"),
                                        answerSet.getInt("id"),
                                        answerSet.getInt("votes"),
                                        answerSet.getDate("added_on"),
                                        getUserById(answerSet.getInt("added_by"))
                                )
                        ),
                        resultSet.getInt("votes"),
                        resultSet.getDate("added_on"),
                        getUserById(resultSet.getInt("added_by"))))
                )
        );
    }

    @Override
    public void addAnswerToQuestionById(AnswerModel answer, int id, String email) {
        int user_id = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id FROM users_db WHERE email=?", new Object[]{email}, ((resultSet, i) -> resultSet.getInt("id")))).get();
        jdbcTemplate.update("INSERT INTO answers_db (id, answer, question_id, votes, added_on, added_by) VALUES (?, ?, ?, ?, ?, ?)", id, answer.getAnswer(), id, 0, new Date(Calendar.getInstance().getTimeInMillis()), user_id);
    }

    @Override
    public UserModel getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT username, email, role FROM users_db WHERE id=?", new Object[]{id}, (resultSet, i) -> new UserModel(
                id,
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("role")
        ));
    }
}
