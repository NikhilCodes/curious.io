package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("QNA_DB")
public class FakeDataAccessObject implements QNADao {
    private static final ArrayList<QNAModel> db = new ArrayList<>(
            List.of(
                    new QNAModel("What is the definition of shit??", 123456, new ArrayList<AnswerModel>(
                            List.of(
                                    new AnswerModel("Just die already!", 123456, 42),
                                    new AnswerModel("Stuff people do after eating stuff.", 7777777, 20)
                            )
                    )),
                    new QNAModel("What is JsonWebToken?", 100000, new ArrayList<AnswerModel>(
                            List.of(
                                    new AnswerModel("Google JWT! Thank me later.", 1000, 102),
                                    new AnswerModel("JSON Web Token is an Internet standard for creating data with optional signature and/or ... JWT relies on other JSON-based standards: JSON Web Signature and JSON Web Encryption", 34324, 20)
                            )
                    )),
                    new QNAModel("Why are you such an Idiot?", 111111, new ArrayList<AnswerModel>(
                            List.of(
                                    new AnswerModel("Coz imma fukin genius", 2124, 1000),
                                    new AnswerModel("IDK, why do you ask?", 2452, 2),
                                    new AnswerModel("Because, I'm an Idiot, I guess! ", 56745735, 31)
                            )
                    ))
            )
    );

    @Override
    public List<QNAModel> get10QNAs(int start) {
        try {
            if (start + 10 >= db.size()) {
                return db.subList(start, db.size());
            }

            return db.subList(start, start + 10);
        } catch (IllegalArgumentException e) {
            return new ArrayList<QNAModel>();
        }
    }

    @Override
    public void addQuestion(QNAModel question, int id) {
        db.add(new QNAModel(question.getQuestion(), id, new ArrayList<AnswerModel>()));
    }

    @Override
    public Optional<QNAModel> getQNAById(int id) {
        return db.stream().filter(qna -> qna.getId() == id).findFirst();
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
