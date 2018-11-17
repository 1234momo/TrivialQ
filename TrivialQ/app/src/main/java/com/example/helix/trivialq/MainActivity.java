package com.example.helix.trivialq;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String correctString = "";
    String questionString = "";
    String optionString2 = "";
    String optionString3 = "";
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    Flashcard cardToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.questionTV)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(0).getAnswer());
        }

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

        //Goes to next set of questions and answers
        findViewById(R.id.nextArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Randomly show a Flashcard if there are flashcards in the database
                if (!allFlashcards.isEmpty()) {
                    currentCardDisplayedIndex = getRandom(0, allFlashcards.size() - 1);
                    ((TextView) findViewById(R.id.questionTV)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
                // When there are no cards in the database, present an empty state
                if (allFlashcards.isEmpty()) {
                    currentCardDisplayedIndex = 0;
                    ((TextView) findViewById(R.id.questionTV)).setText("Add a question");
                    ((TextView) findViewById(R.id.answer1)).setText("");
                    ((TextView) findViewById(R.id.answer2)).setText("");
                    ((TextView) findViewById(R.id.answer3)).setText("");
                }
            }
        });

        //Switch to AddCardActivity to add answer and question
        findViewById(R.id.adding_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        //Deletes a flashcard
        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.questionTV)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                //Display previous Flashcard if there is a previous index and there are Flashcards in the List
                if (currentCardDisplayedIndex - 1 >= 0 && (!allFlashcards.isEmpty() || allFlashcards != null)) {
                    currentCardDisplayedIndex -= 1;
                    ((TextView) findViewById(R.id.questionTV)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
                //Present empty state if there are no Flashcards left and index is 0
                else if (currentCardDisplayedIndex == 0 && (allFlashcards.isEmpty() || allFlashcards == null)) {
                    ((TextView) findViewById(R.id.questionTV)).setText("Add a question");
                    ((TextView) findViewById(R.id.answer1)).setText("");
                    ((TextView) findViewById(R.id.answer2)).setText("");
                    ((TextView) findViewById(R.id.answer3)).setText("");
                }
                //Display the first Flashcard if the index is 0 and there are Flashcards left
                else if (currentCardDisplayedIndex == 0 && (!allFlashcards.isEmpty() || allFlashcards != null)) {
                    currentCardDisplayedIndex = 0;
                    ((TextView) findViewById(R.id.questionTV)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
            }
        });

        //Finds current Flashcard and passes the Flashcard info to AddCardActivity
        findViewById(R.id.edit_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);

                cardToEdit = allFlashcards.get(currentCardDisplayedIndex);

                intent.putExtra("stringKey1", cardToEdit.getQuestion());
                intent.putExtra("stringKey2", cardToEdit.getAnswer());
                intent.putExtra("stringKey3", cardToEdit.getWrongAnswer1());
                intent.putExtra("stringKey4", cardToEdit.getWrongAnswer2());
                MainActivity.this.startActivityForResult(intent, 50);
            }
        });
    }

    //Gets question and answer from AddCardActivity and assigns the data to global variables
    //Displays all data from what user inputted for editText fields for adding or editing a card
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            //Assigns inputs to appropriate variables
            questionString = data.getExtras().getString("question");
            correctString = data.getExtras().getString("option1");
            optionString2 = data.getExtras().getString("option2");
            optionString3 = data.getExtras().getString("option3");

            //Data is assigned to global variables and snackbar is shown if successful
            if (requestCode == 100) {
                //Displays the answer choices
                ((TextView) findViewById(R.id.questionTV)).setText(questionString);
                ((TextView) findViewById(R.id.answer1)).setText(optionString2);
                ((TextView) findViewById(R.id.answer2)).setText(correctString);
                ((TextView) findViewById(R.id.answer3)).setText(optionString3);

                //Insert new Flashcard into database and update allFlashcards
                flashcardDatabase.insertCard(new Flashcard(questionString, correctString, optionString2, optionString3));
                allFlashcards = flashcardDatabase.getAllCards();

                //Shows snackbar to indicate a new Flashcard was created
                Snackbar.make(findViewById(R.id.questionTV), R.string.snackbar_addFlashcard, Snackbar.LENGTH_SHORT).show();
            } else {
                //Set all data to cardToEdit
                cardToEdit.setQuestion(questionString);
                cardToEdit.setAnswer(correctString);
                cardToEdit.setWrongAnswer1(optionString2);
                cardToEdit.setWrongAnswer2(optionString3);

                //Displays the answer choices and question
                ((TextView) findViewById(R.id.questionTV)).setText(questionString);
                ((TextView) findViewById(R.id.answer1)).setText(optionString2);
                ((TextView) findViewById(R.id.answer2)).setText(correctString);
                ((TextView) findViewById(R.id.answer3)).setText(optionString3);

                flashcardDatabase.updateCard(cardToEdit);

                //Shows snackbar to indicate all data is updated
                Snackbar.make(findViewById(R.id.questionTV), R.string.snackbar_editFlashcard, Snackbar.LENGTH_SHORT).show();
            }
        }
        /**
         * When questionString = data.getExtras().getString("question") throws a NullPointerException
         * it signifies that the cancel icon was clicked on.
         *
         * Do nothing if the NullPointerException was thrown
         */ catch (java.lang.NullPointerException e) {
        }
    }

    // Returns a random number between minNumber and maxNumber, inclusive.
    // For example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
    public int getRandom(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
}
