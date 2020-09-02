package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class PostgresDataAccessObject implements QNADao {
    private static final ArrayList<QNAModel> db = new ArrayList<>(
    );
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDataAccessObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<QNAModel> get10QNAs(int start) {
        final String query_sql = String.format("SELECT id, question FROM questions_db OFFSET %d LIMIT 10", start);
        return jdbcTemplate.query(query_sql, (resultSet, i) -> {
            int q_id = resultSet.getInt("id");
            String question = resultSet.getString("question");
            jdbcTemplate.query(String.format("SELECT id, answer, votes FROM answers_db WHERE question_id = %d", q_id), (answerSet, i1) -> {
                return new AnswerModel(answerSet.getString("answer"), answerSet.getInt("id"), answerSet.getInt("votes"));
//                System.out.println(answerSet.getString("answer"));
            });
            return new QNAModel(
                    question,
                    q_id,
                    jdbcTemplate.query(String.format("SELECT id, answer, votes FROM answers_db WHERE question_id = %d", q_id), (answerSet, i1) -> {
                        return new AnswerModel(answerSet.getString("answer"), answerSet.getInt("id"), answerSet.getInt("votes"));
                    })
            );
        });
    }

    @Override
    public void addQuestion(QNAModel question, int id) {
        db.add(new QNAModel(question.getQuestion(), id, new ArrayList<AnswerModel>()));
    }

    @Override
    public Optional<QNAModel> getQNAById(int id) {
        final String query_sql = "SELECT id, question FROM questions_db WHERE id=?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(query_sql, new Object[]{id}, ((resultSet, i) -> {
                    String question = resultSet.getString("question");
                    return new QNAModel(
                            question,
                            id,
                            jdbcTemplate.query(
                                    String.format("SELECT id, answer, votes FROM answers_db WHERE question_id = %d", id),
                                    (answerSet, i1) -> new AnswerModel(
                                            answerSet.getString("answer"),
                                            answerSet.getInt("id"),
                                            answerSet.getInt("votes")
                                    )
                            )
                    );
                }))
        );
    }

    @Override
    public void addAnswerToQuestionById(int id, AnswerModel answer) {
        for (QNAModel qnaModel : db) {
            if (qnaModel.getId() == id) {
                qnaModel.addAnswer(answer);
                return;
            }
        }
    }
}
