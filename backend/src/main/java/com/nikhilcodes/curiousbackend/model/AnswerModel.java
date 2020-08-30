package com.nikhilcodes.curiousbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerModel {
    final private String answer;
    final private int votes;

    public AnswerModel(@JsonProperty("answer") String answer, int votes) {
        this.answer = answer;
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }

    public String getAnswer() {
        return answer;
    }
}
