package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.*;


public class QNAModel {
    final private String question;
    final private String body;
    final private int id;
    final private List<AnswerModel> answers;
    final private int votes;
    final private Date addedOn;

    public QNAModel(@JsonProperty("question") String question,
                    @JsonProperty("body") String body,
                    @JsonProperty("id") int id,
                    @JsonProperty("answers") List<AnswerModel> answers,
                    @JsonProperty("votes") int votes,
                    @JsonProperty("addedOn") Date addedOn) {
        this.body = body;
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.votes = votes;
        this.addedOn = addedOn;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getBody() {
        return body;
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

    public int getVotes() {
        return votes;
    }

    public Date getAddedOn() {
        return addedOn;
    }
}


