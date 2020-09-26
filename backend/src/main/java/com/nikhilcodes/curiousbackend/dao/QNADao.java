package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.utils.RandomIdGenerator;

import java.util.List;
import java.util.Optional;

public interface QNADao {
    List<QNAModel> get10QNAs(int start);

    default void addQuestion(QNAModel question, String email) {
        addQuestion(question, RandomIdGenerator.generate(), email);
    }

    void addQuestion(QNAModel question, int id, String email);

    Optional<QNAModel> getQNAById(int id);

    default void addAnswerToQuestionById(AnswerModel answer, String email) {
        addAnswerToQuestionById(answer, RandomIdGenerator.generate(), email);
    }

    void addAnswerToQuestionById(AnswerModel answer, int id, String email);

    List<Integer> toggleVote(int id, String email);

    List<Integer> upVoteAnswer(int q_id, int a_id, String email);

    List<Integer> downVoteAnswer(int q_id, int a_id, String email);
}
