package com.nikhilcodes.curiousbackend.model;

public class AnswerModel {
    final private String answer;
    final private int votes;

    public AnswerModel(String answer, int votes) {
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
