package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.QNAModel;

import java.util.List;
import java.util.Random;

public interface QNADao {
    public List<QNAModel> get10QNAs(int start);

    default public void addQuestion(QNAModel question) {
        int upperBound = 999999999;
        int lowerBound = 1000;
        addQuestion(question, lowerBound + new Random().nextInt(upperBound - lowerBound));
    }

    void addQuestion(QNAModel question, int id);
}
