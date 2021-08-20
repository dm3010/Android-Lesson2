package com.edu0988.lesson2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class ResultActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Question[] questions = (Question[]) getIntent().getSerializableExtra("questions");
        boolean[] answers = getIntent().getBooleanArrayExtra("answers");
        int rightAnswersCount = 0;

        TextView rightAnswersCountTextView = findViewById(R.id.right_answers_count);
        ListView answersListView = findViewById(R.id.answers_listView);

        ArrayAdapter<Question> adapter = new ArrayAdapter<Question>(this, android.R.layout.two_line_list_item, questions) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                Question question = getItem(position);
                boolean answer = question.isAnswerTrue();
                boolean user_answer = answers[position];

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.two_line_list_item, null);
                }
                TextView text1 = convertView.findViewById(android.R.id.text1);
                TextView text2 = convertView.findViewById(android.R.id.text2);

                text1.setText(question.getQuestionText());
                text2.setText(getString(R.string.your_answer,
                        (answer ? getString(R.string.yes)
                                : getString(R.string.no))));

                convertView.setBackgroundColor(user_answer == question.isAnswerTrue() ?
                        getColor(android.R.color.holo_green_light) :
                        getColor(android.R.color.holo_red_light));

                return convertView;
            }
        };

        for (int i = 0; i < questions.length; i++) {
            if (answers[i] == questions[i].isAnswerTrue()) rightAnswersCount++;
        }

        answersListView.setAdapter(adapter);
        rightAnswersCountTextView.setText(getString(R.string.right_count, rightAnswersCount));

    }
}