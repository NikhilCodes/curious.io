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
                    new QNAModel("What is the definition of shit??", 123456, new ArrayList<>(
                            List.of(new AnswerModel("Stuff that comes out after you eat!", 4), new AnswerModel("Just die already!", 20))
                    )),
                    new QNAModel("What is JsonWebToken?", 100000, new ArrayList<>(
                            List.of(new AnswerModel("JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed.\n", 10))
                    )),
                    new QNAModel("Why are you such an Idiot?", 111111, new ArrayList<>(
                            List.of(new AnswerModel("Becoz I'm a fukin Genius!", 30))
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
        db.add(new QNAModel(question.getQuestion(), id, new ArrayList<>()));
    }

    @Override
    public Optional<QNAModel> getQNAById(int id) {
        return db.stream().filter(qna -> qna.getId() == id).findFirst();
    }
}
