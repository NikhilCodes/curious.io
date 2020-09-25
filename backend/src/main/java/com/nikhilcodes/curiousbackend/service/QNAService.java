package com.nikhilcodes.curiousbackend.service;

import com.nikhilcodes.curiousbackend.dao.QNADao;
import com.nikhilcodes.curiousbackend.model.AnswerModel;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QNAService {
    private final QNADao qnaDao;

    @Autowired
    public QNAService(@Qualifier("qna-data") QNADao qnaDao) {
        this.qnaDao = qnaDao;
    }

    public List<QNAModel> get10QNAs(int start) {
        return qnaDao.get10QNAs(start);
    }

    public void addQuestion(QNAModel question, String email) {
        qnaDao.addQuestion(question, email);
    }

    public Optional<QNAModel> getQNA(int id) {
        return qnaDao.getQNAById(id);
    }

    public void addAnswer(AnswerModel answer, int id, String email) {
        qnaDao.addAnswerToQuestionById(answer, id, email);
    }

    public void toggleVote(int id, String email) {
        qnaDao.toggleVote(id, email);
    }
}
