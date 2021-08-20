package com.edu0988.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView answerTextView;
    private boolean isAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answerTextView = findViewById(R.id.answerTextView);

        isAnswerTrue = getIntent().getBooleanExtra("answer", false);
        answerTextView.setText(isAnswerTrue ? R.string.yes : R.string.no);
    }
}