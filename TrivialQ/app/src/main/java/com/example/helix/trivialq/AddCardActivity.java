package com.example.helix.trivialq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        //Get previous question and answer choices
        String prevQuestion = getIntent().getStringExtra("stringKey1");
        String prevOption_1 = getIntent().getStringExtra("stringKey2");
        String prevOption_2 = getIntent().getStringExtra("stringKey3");
        String prevOption_3 = getIntent().getStringExtra("stringKey4");

        //Get the editTexts to display previous data
        EditText questionTV = ((EditText) findViewById(R.id.question));
        EditText option_1TV = ((EditText) findViewById(R.id.answer_1));
        EditText option_2TV = ((EditText) findViewById(R.id.answer_2));
        EditText option_3TV = ((EditText) findViewById(R.id.answer_3));

        //Displays previous data, if any
        questionTV.setText(prevQuestion);
        option_1TV.setText(prevOption_1);
        option_2TV.setText(prevOption_2);
        option_3TV.setText(prevOption_3);

        //Cancel adding a question and answer
        findViewById(R.id.cancel_adding_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(50);
                finish();
            }
        });

        //Submits info and sends question and answer to main activity
        findViewById(R.id.submit_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieves input data
                String question = ((EditText) findViewById(R.id.question)).getText().toString().trim();
                String option_1 = ((EditText) findViewById(R.id.answer_1)).getText().toString().trim();
                String option_2 = ((EditText) findViewById(R.id.answer_2)).getText().toString().trim();
                String option_3 = ((EditText) findViewById(R.id.answer_3)).getText().toString().trim();

                //Checks if all textfields were entered
                if (question.isEmpty() || option_1.isEmpty() || option_2.isEmpty()|| option_3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter question and all answers", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Packages data and send to MainActivity
                    Intent data = new Intent(); // create a new Intent, this is where we will put our data
                    data.putExtra("question", question); // puts question string into the Intent, with the key as 'question'
                    data.putExtra("option1", option_1);
                    data.putExtra("option2", option_2);
                    data.putExtra("option3", option_3);
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish();
                }
            }
        });
    }
}
