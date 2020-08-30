package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class QNAModel {
    final private String question;
    final private int id;
    final private List<AnswerModel> answers;

    public QNAModel(@JsonProperty("question") String question,
                    @JsonProperty("id") int id,
                    @JsonProperty("answers") List<AnswerModel> answers) {
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

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void addAnswer(AnswerModel answer) {
        answers.add(new AnswerModel(answer.getAnswer(), answer.getAnswer().length()));
    }

    public Optional<AnswerModel> getTopAnswer() {
        return answers.stream().max(Comparator.comparingInt(AnswerModel::getVotes));
    }
}


