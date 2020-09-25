package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Array;
import java.sql.Date;
import java.util.*;


public class QNAModel {
    final private String question;
    final private String body;
    final private int id;
    final private List<AnswerModel> answers;
    final private List<Integer> votes;
    final private Date addedOn;
    final private UserModel addedBy;

    public QNAModel(@JsonProperty("question") String question,
                    @JsonProperty("body") String body,
                    @JsonProperty("id") int id,
                    @JsonProperty("answers") List<AnswerModel> answers,
                    @JsonProperty("votes") List<Integer> votes,
                    @JsonProperty("addedOn") Date addedOn,
                    @JsonProperty("addedBy") UserModel addedBy) {
        this.body = body;
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.votes = votes;
        this.addedOn = addedOn;
        this.addedBy = addedBy;
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

//      //Commenting out below function, cuz it's kinda redundant for now.
//    public Optional<AnswerModel> getTopAnswer() {
//        return answers.stream().max(Comparator.comparingInt(AnswerModel::getVotes));
//    }

    public List<Integer> getVotes() {
        return votes;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public UserModel getAddedBy() {
        return addedBy;
    }
}


