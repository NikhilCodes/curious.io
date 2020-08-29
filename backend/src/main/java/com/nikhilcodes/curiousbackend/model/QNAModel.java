package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class QNAModel {
    final private String question;
    final private int id;
    final private ArrayList<AnswerModel> answers;

    public QNAModel(@JsonProperty("question") String question,
                    @JsonProperty("id") int id,
                    @JsonProperty("answers") ArrayList<AnswerModel> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<AnswerModel> getAnswers() {
        return answers;
    }

    public Optional<AnswerModel> getTopAnswer() {
        return answers.stream().max(Comparator.comparingInt(AnswerModel::getVotes));
    }
}


