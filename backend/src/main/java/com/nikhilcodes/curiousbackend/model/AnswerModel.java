package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikhilcodes.curiousbackend.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class AnswerModel {
    final private String answer;
    final private List<Integer> upVotes, downVotes;
    final private int id;
    final private Date addedOn;
    final private UserModel addedBy;

    public AnswerModel(@JsonProperty("answer") String answer, @JsonProperty("id") int id, List<Integer> upVotes, List<Integer> downVotes, Date addedOn, UserModel addedBy) {
        this.answer = answer;
        this.id = id;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.addedOn = addedOn;
        this.addedBy = addedBy;
    }

    public List<Integer> getUpVotes() {
        return upVotes;
    }

    public List<Integer> getDownVotes() {
        return downVotes;
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
