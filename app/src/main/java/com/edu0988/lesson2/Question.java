package com.edu0988.lesson2;

import java.io.Serializable;

public class Question implements Serializable {
    private final int questionText;
    private final boolean answerTrue;

    public Question(int questionText, boolean answerTrue) {
        this.questionText = questionText;
        this.answerTrue = answerTrue;
    }

    public int getQuestionText() {
        return questionText;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }
}