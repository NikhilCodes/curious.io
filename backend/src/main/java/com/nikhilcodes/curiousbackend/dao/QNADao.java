package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import com.nikhilcodes.curiousbackend.utils.RandomIdGenerator;

import java.util.List;
import java.util.Optional;

public interface QNADao {
    List<QNAModel> get10QNAs(int start);

    default void addQuestion(QNAModel question) {
        addQuestion(question, RandomIdGenerator.generate());
    }

    void addQuestion(QNAModel question, int id);

    Optional<QNAModel> getQNAById(int id);

    void addAnswerToQuestionById(int id, AnswerModel answer);
}
