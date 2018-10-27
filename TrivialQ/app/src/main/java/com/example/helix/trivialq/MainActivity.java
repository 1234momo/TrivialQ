package com.example.helix.trivialq;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    boolean isShowingAnswers = true;
    String correctString = "";
    String questionString = "";
    String optionString2 = "";
    String optionString3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         * Showing answer and hiding question
//
//        findViewById(R.id.questionTV).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.answerTV).setVisibility(View.VISIBLE);
//                findViewById(R.id.questionTV).setVisibility(View.INVISIBLE);
//            }
//        });
//
//        /**
//         * Showing question and hiding answer
//
//        findViewById(R.id.answerTV).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.answerTV).setVisibility(View.INVISIBLE);
//                findViewById(R.id.questionTV).setVisibility(View.VISIBLE);
//            }
//        });
//        findViewById(R.id.closed_eye_Img).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
//                findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
//                findViewById(R.id.answer3).setVisibility(View.INVISIBLE);
//                findViewById(R.id.closed_eye_Img).setVisibility(View.INVISIBLE);
//                findViewById(R.id.open_eye_Img).setVisibility(View.VISIBLE);
//                isShowingAnswers = false;
//            }
//        });
//
//        findViewById(R.id.open_eye_Img).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.closed_eye_Img).setVisibility(View.VISIBLE);
//                findViewById(R.id.open_eye_Img).setVisibility(View.INVISIBLE);
//                findViewById(R.id.answer1).setVisibility(View.VISIBLE);
//                findViewById(R.id.answer2).setVisibility(View.VISIBLE);
//                findViewById(R.id.answer3).setVisibility(View.VISIBLE);
//                isShowingAnswers = true;
//            }
//        });

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

        //Resets all Textviews to gold color
        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer3).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
                findViewById(R.id.answer2).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
                findViewById(R.id.answer1).setBackgroundColor(getResources().getColor(R.color.GOLD, null));
            }
        });

        //Switch to AddCardActivity to add answer and question
        findViewById(R.id.adding_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

        //Gets question and answer from AddCArdActivity and calls onActivityResult method
        findViewById(R.id.edit_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("stringKey1", questionString);
                intent.putExtra("stringKey2", correctString);
                intent.putExtra("stringKey3", optionString2);
                intent.putExtra("stringKey4", optionString3);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    //Gets question and answer from AddCardActivity and assigns the data to global variables
    //Displays all data to what user inputted for editTect fields
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Data is assigned to global variables and snackbar is shown if successful
        if (requestCode == 100) {
            //Assigns inputs to appropriate variables
            try {
                questionString = data.getExtras().getString("question");
                correctString = data.getExtras().getString("option1");
                optionString2 = data.getExtras().getString("option2");
                optionString3 = data.getExtras().getString("option3");


                TextView questionText = (TextView) findViewById(R.id.questionTV);
                TextView option1Text = (TextView) findViewById(R.id.answer1);
                TextView option2Text = (TextView) findViewById(R.id.answer2);
                TextView option3Text = (TextView) findViewById(R.id.answer3);

                //Displays the answer choices
                questionText.setText(questionString);
                option1Text.setText(optionString2);
                option2Text.setText(correctString);
                option3Text.setText(optionString3);

                //Shows snackbar to indicate all data is updated
                Snackbar.make(questionText, R.string.snackbar_text, Snackbar.LENGTH_SHORT).show();
            }
            /**
             * When questionString = data.getExtras().getString("question") throws a NullPointerException
             * it signifies that the cancel icon was clicked on.
             *
             * Do nothing if the NullPointerException was thrown
             */
            catch (java.lang.NullPointerException e)
            {
            }
        }
    }
}
