package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;


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
        int upperBound = 999999999;
        int lowerBound = 1000;
        answers.add(new AnswerModel(answer.getAnswer(), (lowerBound + new Random().nextInt(upperBound - lowerBound)), answer.getAnswer().length()));
    }

    public Optional<AnswerModel> getTopAnswer() {
        return answers.stream().max(Comparator.comparingInt(AnswerModel::getVotes));
    }
}


