package com.example.android.quizprojectfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class QuizQuestionsActivity extends AppCompatActivity {

    // User Answers
    boolean answerOne, answerTwoOne, answerTwoTwo, answerTwoThree, answerThree, answerFour;

    // Score counter variables
    String toast;
    int score;

    // saving instances
    static final String playerAnswerOne = "Players Answer 1";
    static final String playerAnswerTwoOne = "Players Score 1";
    static final String playerAnswerTwoTwo = "Players Score 2";
    static final String playerAnswerTwoThree = "Players Score 3";
    static final String playerAnswerThree = "Players Score 4";
    static final String playerAnswerFour = "Players Score 5";

    // Enter variables
    EditText nameInput;
    CheckBox answer2_1, answer2_2, answer2_3, answer2_4, answer2_5, answer2_6, answer2_7, answer2_8, answer2_9, answer2_10;
    RadioButton answer3, answer4;
    RadioGroup answer3group, answer4group;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        if (savedInstanceState != null) {
            answerTwoOne = savedInstanceState.getBoolean(playerAnswerTwoOne);
            answerTwoTwo = savedInstanceState.getBoolean(playerAnswerTwoTwo);
            answerTwoThree = savedInstanceState.getBoolean(playerAnswerTwoThree);
            answerThree = savedInstanceState.getBoolean(playerAnswerThree);
            answerFour = savedInstanceState.getBoolean(playerAnswerFour);
            answerOne = savedInstanceState.getBoolean(playerAnswerOne);
        }

        nameInput = (EditText) findViewById(R.id.witchers_name);
        answer2_1 = (CheckBox) findViewById(R.id.answer_two_1);
        answer2_2 = (CheckBox) findViewById(R.id.answer_two_2);
        answer2_3 = (CheckBox) findViewById(R.id.answer_two_3);
        answer2_4 = (CheckBox) findViewById(R.id.answer_two_4);
        answer2_5 = (CheckBox) findViewById(R.id.answer_two_5);
        answer2_6 = (CheckBox) findViewById(R.id.answer_two_6);
        answer2_7 = (CheckBox) findViewById(R.id.answer_two_7);
        answer2_8 = (CheckBox) findViewById(R.id.answer_two_8);
        answer2_9 = (CheckBox) findViewById(R.id.answer_two_9);
        answer2_10 = (CheckBox) findViewById(R.id.answer_two_10);
        answer3 = (RadioButton) findViewById(R.id.answer_three_1);
        answer4 = (RadioButton) findViewById(R.id.answer_four_2);
        answer3group = (RadioGroup) findViewById(R.id.question_3_group);
        answer4group = (RadioGroup) findViewById(R.id.question_4_group);
        scroll = (ScrollView) findViewById(R.id.scroll_view_id);
    }

    /*
    onSaveInstance
    */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(playerAnswerTwoOne, answerTwoOne);
        savedInstanceState.putBoolean(playerAnswerTwoTwo, answerTwoTwo);
        savedInstanceState.putBoolean(playerAnswerTwoThree, answerTwoThree);
        savedInstanceState.putBoolean(playerAnswerThree, answerThree);
        savedInstanceState.putBoolean(playerAnswerFour, answerFour);
        savedInstanceState.putBoolean(playerAnswerOne, answerOne);

        super.onSaveInstanceState(savedInstanceState);
    }

    /*
    Retrieves first answer entered value
     */
    public String witchersName() {
        String name = nameInput.getText().toString();
        return name;
    }

    /*
    Retrieves second answer entered values
     */
    public void isClicked(View view) {
        answerTwoOne = answer2_1.isChecked();
        answerTwoTwo = answer2_2.isChecked();
        answerTwoThree = answer2_3.isChecked();
        answerThree = answer3.isChecked();
        answerFour = answer4.isChecked();
    }

    /*
     Counts the final score
      */
    public int scoreCounter() {
        score = 0;
        // Checks the first answer if entered correctly and adds points if true
        answerOne = witchersName().equalsIgnoreCase(getString(R.string.question_one_answer));
        if (answerOne) {
            score = score + 1;
        }

        // Checks the second answer if entered correctly and adds points if true
        if (answerTwoOne) {
            score++;
        }
        if (answerTwoTwo) {
            score = score + 1;
        }
        if (answerTwoThree) {
            score = score + 1;
        }

        // Checks third asnwer if entered correctly and adds points if true
        if (answerThree) {
            score = score + 1;
        }

        // Checks fourth asnwer if entered correctly and adds points if true
        if (answerFour) {
            score = score + 1;
        }
        return score;
    }

    /*
    Creates a congratulations message
     */
    public String finalMessage(int score) {
        toast = getString(R.string.congratulations);
        toast = toast + "\n" + getString(R.string.final_score) + " " + score + getString(R.string.max_score);
        return toast;
    }

    /*
    Displays a toast with congratulations message or Error message if user did not answer to all questions
     */
    public void displayResults(View view) {
        if ((questionOneEmpty() || questionTwoEmpty() || questionThreeEmpty() || questionFourEmpty())) {
            displayToast(errorMessage());
        } else if(questionTwoNumberOfAnswers()) {
            displayToast(getString(R.string.question_2_error));
        } else {
            displayToast(finalMessage(scoreCounter()));
        }
    }

    /*
    Displays toast message
    @toastText is a String value that is going to be displayed in toast message
     */
    private void displayToast(String toastText) {
        Context context = getApplicationContext();
        CharSequence text = toastText;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /*
    Restes the app
     */
    public void scoreReset(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        score = 0;
        nameInput.setText(null);
        answer2_1.setChecked(false);
        answer2_2.setChecked(false);
        answer2_3.setChecked(false);
        answer2_4.setChecked(false);
        answer2_5.setChecked(false);
        answer2_6.setChecked(false);
        answer2_7.setChecked(false);
        answer2_8.setChecked(false);
        answer2_9.setChecked(false);
        answer2_10.setChecked(false);
        answer3group.clearCheck();
        answer4group.clearCheck();
        scroll.fullScroll(ScrollView.FOCUS_UP);
    }

    /*
    Checks if question one is not left blank
     */
    private boolean questionOneEmpty() {
        if (TextUtils.isEmpty(witchersName())) {
            return true;
        }
        return false;
    }

    /*
    Checks if question two is not left blank
     */
    private boolean questionTwoEmpty() {
        if (!(answer2_1.isChecked() || answer2_2.isChecked() || answer2_3.isChecked() || answer2_4.isChecked() || answer2_5.isChecked() || answer2_6.isChecked() || answer2_7.isChecked() || answer2_8.isChecked() || answer2_9.isChecked() || answer2_10.isChecked())) {
            return true;
        }
        return false;
    }

    /*
    Checks if question three is not left blank
     */
    private boolean questionThreeEmpty() {
        if (answer3group.getCheckedRadioButtonId() == -1) {
            return true;
        }
        return false;
    }

    /*
    Checks if question four is not left blank
     */
    private boolean questionFourEmpty() {
        if (answer4group.getCheckedRadioButtonId() == -1) {
            return true;
        }
        return false;
    }

    /*
    Creates toast error message if any answer left blank
     */
    private String errorMessage() {
        toast = null;
        if (questionOneEmpty()) {
            toast = getString(R.string.empty_answer_1);
        }
        if (questionTwoEmpty()) {
            if (toast != null) {
                toast = toast + "\n" + getString(R.string.empty_answer_2);
            } else {
                toast = getString(R.string.empty_answer_2);
            }
        }
        if (questionThreeEmpty()) {
            if (toast != null) {
                toast = toast + "\n" + getString(R.string.empty_answer_3);
            } else {
                toast = getString(R.string.empty_answer_3);
            }
        }
        if (questionFourEmpty()) {
            if (toast != null) {
                toast = toast + "\n" + getString(R.string.empty_answer_4);
            } else {
                toast = getString(R.string.empty_answer_4);
            }
        }
        return toast;
    }

    /*
    Checks how many answers in question 2 are marked
     */
    private boolean questionTwoNumberOfAnswers() {
        int numberofAnswers = 0;
        if (answer2_1.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_2.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_3.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_4.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_5.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_6.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_7.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_8.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_9.isChecked()) {
            numberofAnswers++;
        }
        if (answer2_10.isChecked()) {
            numberofAnswers++;
        }
        if (numberofAnswers > 3) {
            return true;
        }
        return false;
    }
}
