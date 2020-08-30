package com.nikhilcodes.curiousbackend.service;

import com.nikhilcodes.curiousbackend.dao.QNADao;
import com.nikhilcodes.curiousbackend.model.QNAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QNAService {
    final private QNADao qnaDao;

    @Autowired
    public QNAService(@Qualifier("QNA_DB") QNADao qnaDao) {
        this.qnaDao = qnaDao;
    }

    public List<QNAModel> get10QNAs(int start) {
        return qnaDao.get10QNAs(start);
    }

    public void addQuestion(QNAModel question) {
        qnaDao.addQuestion(question);
    }

    public Optional<QNAModel> getQNA(int id) {
        return qnaDao.getQNAById(id);
    }
}
