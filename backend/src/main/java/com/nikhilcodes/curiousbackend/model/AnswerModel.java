package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerModel {
    final private String answer;
    final private int votes;
    final private int id;

    public AnswerModel(@JsonProperty("answer") String answer, @JsonProperty("_id") int id, int votes) {
        this.answer = answer;
        this.id = id;
        this.votes = votes;
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
}
