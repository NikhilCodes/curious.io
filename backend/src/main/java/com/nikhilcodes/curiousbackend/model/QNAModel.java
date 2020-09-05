package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.*;


public class QNAModel {
    final private String question;
    final private int id;
    final private List<AnswerModel> answers;
    final private Date addedOn;

    public QNAModel(@JsonProperty("question") String question,
                    @JsonProperty("id") int id,
                    @JsonProperty("answers") List<AnswerModel> answers,
                    @JsonProperty("addedOn") Date addedOn) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.addedOn = addedOn;
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
        answers.add(new AnswerModel(
                answer.getAnswer(),
                (lowerBound + new Random().nextInt(upperBound - lowerBound)),
                answer.getAnswer().length(),
                new Date(Calendar.getInstance().getTimeInMillis()))
        );
    }

    public Optional<AnswerModel> getTopAnswer() {
        return answers.stream().max(Comparator.comparingInt(AnswerModel::getVotes));
    }

    public Date getAddedOn() {
        return addedOn;
    }
}


