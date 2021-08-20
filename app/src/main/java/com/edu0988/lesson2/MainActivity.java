package com.edu0988.lesson2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    Button showAnswerBtn;
    TextView questionTextView;
    Toast toast;

    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };

    int questionIndex = 0;
    boolean[] userAnswers = new boolean[questions.length];

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionIndex", questionIndex);
        outState.putBooleanArray("userAnswers", userAnswers);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);
        showAnswerBtn = findViewById(R.id.showAnswerBtn);

        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("questionIndex");
            userAnswers = savedInstanceState.getBooleanArray("userAnswers");
        }

        yesBtn.setOnClickListener(onClickListener);
        noBtn.setOnClickListener(onClickListener);
        showAnswerBtn.setOnClickListener(showAnswerButtonOnClickListener);

        questionTextView.setText(questions[questionIndex].getQuestionText());
    }

    View.OnClickListener showAnswerButtonOnClickListener = v -> {
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
        startActivity(intent);
    };

    View.OnClickListener onClickListener = v -> {

        if (questions[questionIndex].isAnswerTrue() == (v == yesBtn))
            toastShow(getString(R.string.right));
        else
            toastShow(getString(R.string.wrong));

        userAnswers[questionIndex] = v == yesBtn;

        questionIndex++;

        if (questionIndex < questions.length) {
            questionTextView.setText(questions[questionIndex].getQuestionText());
        } else {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("questions", questions);
            intent.putExtra("answers", userAnswers);

            startActivity(intent);
            finish();
        }
    };

    private void toastShow(CharSequence message) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}