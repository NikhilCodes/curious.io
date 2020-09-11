package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikhilcodes.curiousbackend.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class AnswerModel {
    final private String answer;
    final private int votes;
    final private int id;
    final private Date addedOn;
    final private UserModel addedBy;

    public AnswerModel(@JsonProperty("answer") String answer, @JsonProperty("id") int id, int votes, Date addedOn, UserModel addedBy) {
        this.answer = answer;
        this.id = id;
        this.votes = votes;
        this.addedOn = addedOn;
        this.addedBy = addedBy;
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

    public UserModel getAddedBy() {
        return addedBy;
    }
}
