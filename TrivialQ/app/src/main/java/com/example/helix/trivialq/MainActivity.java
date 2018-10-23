package com.example.helix.trivialq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Showing answer and hiding question

        findViewById(R.id.questionTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answerTV).setVisibility(View.VISIBLE);
                findViewById(R.id.questionTV).setVisibility(View.INVISIBLE);
            }
        });

        /**
         * Showing question and hiding answer

        findViewById(R.id.answerTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answerTV).setVisibility(View.INVISIBLE);
                findViewById(R.id.questionTV).setVisibility(View.VISIBLE);
            }
        });
         */
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.RED, null));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.GREEN, null));
            }
        });

        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.GREEN, null));
            }
        });

        findViewById(R.id.answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.RED, null));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.GREEN, null));
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
            }
        });

        findViewById(R.id.closed_eye_Img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
                findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
                findViewById(R.id.answer3).setVisibility(View.INVISIBLE);
                findViewById(R.id.closed_eye_Img).setVisibility(View.INVISIBLE);
                findViewById(R.id.open_eye_Img).setVisibility(View.VISIBLE);
                isShowingAnswers = false;
            }
        });

        findViewById(R.id.open_eye_Img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.closed_eye_Img).setVisibility(View.VISIBLE);
                findViewById(R.id.open_eye_Img).setVisibility(View.INVISIBLE);
                findViewById(R.id.answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.answer3).setVisibility(View.VISIBLE);
                isShowingAnswers = true;
            }
        });

        findViewById(R.id.adding_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
