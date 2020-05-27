package com.clp.community.dto;

import com.clp.community.mode.Question;
import com.clp.community.mode.User;

public class QuestionDTO extends Question {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
