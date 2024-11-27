package com.example.mappeinnlevering1s374926;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Game extends AppCompatActivity implements MyDialogue.MyInterface {

    // Define needed variables
    private String[] problems;
    private int[] correctAnswers;
    private ArrayList<Integer> problemOrder;
    private int currentProblemIndex = 0;
    private StringBuilder currentAnswer = new StringBuilder();
    private int correctCount = 0;
    private TextView problemText;
    private TextView answerInput;
    private TextView feedbackText;
    private int maxProblems;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Start game", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Stop game", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Destroy game", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Pause game", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Resume game", "onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Create game", "onCreate");
        super.onCreate(savedInstanceState);

        // Load and set language preference
        SharedPreferences preferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        String language = preferences.getString("language", "");

        // If there is no saved language, set the default to the system language
        if (language.isEmpty()) {
            language = Locale.getDefault().getLanguage(); // Get the system language
            // Save the system language as the default language
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("language", language);
            editor.apply();
        }

        setLocale(language); // Set the language

        setContentView(R.layout.game);

        Button buttonBackToMainMenu2 = findViewById(R.id.buttonBackToMainMenu2);

        buttonBackToMainMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogue(view);  // Show dialog when button is clicked
            }
        });

        problemText = findViewById(R.id.problemText);
        answerInput = findViewById(R.id.answerInput);
        feedbackText = findViewById(R.id.feedbackText);

        // Load number of problems from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        maxProblems = sharedPreferences.getInt("numberOfProblems", 5);  // Default to 5 if no value is saved

        // Load problems and answers
        problems = getResources().getStringArray(R.array.addition_problems);
        correctAnswers = getResources().getIntArray(R.array.correct_answers);

        // Randomize the order of problems
        problemOrder = new ArrayList<>();
        for (int i = 0; i < problems.length; i++) {
            problemOrder.add(i);
        }
        Collections.shuffle(problemOrder);

        // Limit the number of problems to maxProblems
        if (problemOrder.size() > maxProblems) {
            problemOrder = new ArrayList<>(problemOrder.subList(0, maxProblems));
        }

        // Display problem
        showNextProblem();

        // Set listeners for number buttons
        setNumberButtonListeners();

        // Submit button listener
        Button submitAnswer = findViewById(R.id.submitAnswer);
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    // Show next problem
    private void showNextProblem() {
        if (currentProblemIndex < problemOrder.size()) {
            int problemIndex = problemOrder.get(currentProblemIndex);
            problemText.setText(problems[problemIndex]);
            answerInput.setText(""); // Clear answer input
            currentAnswer.setLength(0); // Clear current answer
        } else {
            showGameCompleteDialog(); // Show dialog when the game is completed
        }
    }

    // Set listeners for number buttons
    private void setNumberButtonListeners() {
        int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9};

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentAnswer.append(button.getText().toString()); // Append button text to currentAnswer
                    answerInput.setText(currentAnswer.toString()); // Update the answer input TextView
                }
            });
        }
    }

    // Check the current answer
    private void checkAnswer() {
        if (currentAnswer.length() > 0) {
            int problemIndex = problemOrder.get(currentProblemIndex);
            int submittedAnswer = Integer.parseInt(currentAnswer.toString());

            if (submittedAnswer == correctAnswers[problemIndex]) {
                // Correct answer
                correctCount++;
                feedbackText.setTextColor(getResources().getColor(R.color.green)); // Set text color to green
                feedbackText.setText(getString(R.string.goodJob)+"!"+getString(R.string.bigSmileyEmoji)+" "+getString(R.string.correctAnswer)+" "+ correctAnswers[problemIndex]+"."); // Show feedback
            } else {
                // Wrong answer
                feedbackText.setTextColor(getResources().getColor(R.color.dark_red)); // Set text color to red
                feedbackText.setText(getString(R.string.almost)+"!"+getString(R.string.bigSmileyEmoji)+" "+getString(R.string.correctAnswer)+" "+ correctAnswers[problemIndex]+"."); // Show feedback
            }

            currentProblemIndex++;

            // Check if there are more problems to show
            if (currentProblemIndex < problemOrder.size()) {
                showNextProblem(); // Show the next problem
            } else {
                // No more problems, show the game completed dialog
                feedbackText.setText(""); // Clear the feedback text
                showGameCompleteDialog(); // Show dialog when the game is completed
            }
        } else {
            feedbackText.setTextColor(getResources().getColor(R.color.dark_red)); // Set text color to green
            feedbackText.setText(R.string.pleaseEnterAnAnswer); // Show feedback
        }
    }

    // Show dialog when the game is completed
    private void showGameCompleteDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_finished_game, null);

        TextView gameCompletedTitle = dialogView.findViewById(R.id.gameCompletedTitle);
        TextView gameCompletedMessage = dialogView.findViewById(R.id.gameCompletedMessage);
        Button confirm_button = dialogView.findViewById(R.id.confirm_button);

        // Set the title text
        gameCompletedTitle.setText(getString(R.string.gameCompleted)+"! "+getString(R.string.celebrationEmoji));

        // Set the message based on performance
        if ((double) correctCount / maxProblems == 1) { // 100% correct
            gameCompletedMessage.setText(getString(R.string.perfectJob) + "!"+getString(R.string.bigSmileyEmoji)+"  " + correctCount + "/" + maxProblems + " " + getString(R.string.correct)+".");
        } else if ((double) correctCount / maxProblems < 1 && (double) correctCount / maxProblems >= 0.8) { // 99%-80% correct
            gameCompletedMessage.setText(getString(R.string.veryGoodJob) + "!"+getString(R.string.bigSmileyEmoji)+"  " + correctCount + "/" + maxProblems + " " + getString(R.string.correct)+".");
        } else if ((double) correctCount / maxProblems < 0.8 && (double) correctCount / maxProblems >= 0.4) { // 80%-40% correct
            gameCompletedMessage.setText(getString(R.string.goodJob) + "!"+getString(R.string.smileyEmoji)+"  " + correctCount + "/" + maxProblems + " " + getString(R.string.correct)+".");
        } else { // < 40% correct
            gameCompletedMessage.setText(getString(R.string.tooBad) + "!"+getString(R.string.sadFaceEmoji)+"  " + correctCount + "/" + maxProblems + " " + getString(R.string.correct)+".");
        }

        // Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false); // Prevent dismiss with back button
        final AlertDialog dialog = builder.create();

        // Set the custom button's onClick listener
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to MainActivity when user clicks "OK"
                Intent i = new Intent(Game.this, MainActivity.class);
                startActivity(i);
                finish(); // Finish this activity
                dialog.dismiss(); // Close the dialog
            }
        });

        // Show the dialog
        dialog.show();
    }

    // Method to display the dialog
    public void showDialogue(View v) {
        DialogFragment dialog = new MyDialogue();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    // Interface methods from MyInterface
    @Override
    public void onYesClick() {
        // Return to MainActivity when user clicks "Yes"
        Intent i = new Intent(Game.this, MainActivity.class);
        startActivity(i);
        finish(); // Finish this activity
    }

    @Override
    public void onNoClick() {
        // Do nothing if the user clicks "Cancel"
    }

    // Set the language
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
