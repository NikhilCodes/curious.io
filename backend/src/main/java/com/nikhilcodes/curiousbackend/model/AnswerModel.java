package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class AnswerModel {
    final private String answer;
    final private int votes;
    final private int id;
    final private Date addedOn;

    public AnswerModel(@JsonProperty("answer") String answer, @JsonProperty("_id") int id, int votes, Date addedOn) {
        this.answer = answer;
        this.id = id;
        this.votes = votes;
        this.addedOn = addedOn;
    }

    public int getVotes() {
        return votes;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public Date getAddedOn() {
        return addedOn;
    }
}
