package com.example.mappeinnlevering1s374926;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Start main activity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Stop main activity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Destroy main activity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Pause main activity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Resume main activity", "onResume");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create main activity", "onCreate");

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

        setContentView(R.layout.activity_main);

        // Get the numbers
        TextView number0 = findViewById(R.id.number0);
        TextView number1 = findViewById(R.id.number1);
        TextView number2 = findViewById(R.id.number2);
        TextView number3 = findViewById(R.id.number3);
        TextView number4 = findViewById(R.id.number4);
        TextView number5 = findViewById(R.id.number5);
        TextView number6 = findViewById(R.id.number6);
        TextView number7 = findViewById(R.id.number7);
        TextView number8 = findViewById(R.id.number8);
        TextView number9 = findViewById(R.id.number9);

        // Animation number0
        ObjectAnimator animator0Y = ObjectAnimator.ofFloat(number0, "translationY", -400f, 600);
        ObjectAnimator animator0X = ObjectAnimator.ofFloat(number0, "translationX", -800f, 600);
        animator0Y.setDuration(4000); // 4 seconds
        animator0X.setDuration(2000); // 2 seconds
        animator0Y.setRepeatCount(ValueAnimator.INFINITE);
        animator0X.setRepeatCount(ValueAnimator.INFINITE);
        animator0Y.setRepeatMode(ValueAnimator.REVERSE);
        animator0X.setRepeatMode(ValueAnimator.REVERSE);
        animator0Y.setInterpolator(new LinearInterpolator());
        animator0X.setInterpolator(new LinearInterpolator());
        animator0Y.start();
        animator0X.start();

        // Animation number1
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(number1, "translationY", -900f, 200f);
        animator1.setDuration(3000); // 3 seconds
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.start();

        // Animation number2
        ObjectAnimator animator2X = ObjectAnimator.ofFloat(number2, "translationX", -900f, -100f);
        ObjectAnimator animator2Y = ObjectAnimator.ofFloat(number2, "translationY", 700f, -700f);
        animator2X.setDuration(4000); // 4 seconds
        animator2Y.setDuration(5000); // 5 seconds
        animator2X.setRepeatCount(ValueAnimator.INFINITE);
        animator2Y.setRepeatCount(ValueAnimator.INFINITE);
        animator2X.setRepeatMode(ValueAnimator.REVERSE);
        animator2Y.setRepeatMode(ValueAnimator.REVERSE);
        animator2X.setInterpolator(new LinearInterpolator());
        animator2Y.setInterpolator(new LinearInterpolator());
        animator2X.start();
        animator2Y.start();

        // Animation number3
        ObjectAnimator animator3X = ObjectAnimator.ofFloat(number3, "translationX", 900f, -800f);
        ObjectAnimator animator3Y = ObjectAnimator.ofFloat(number3, "translationY", 1000f, -250f);
        animator3X.setDuration(5000); // 5 seconds
        animator3Y.setDuration(5000); // 5 seconds
        animator3X.setRepeatCount(ValueAnimator.INFINITE);
        animator3Y.setRepeatCount(ValueAnimator.INFINITE);
        animator3X.setRepeatMode(ValueAnimator.REVERSE);
        animator3Y.setRepeatMode(ValueAnimator.REVERSE);
        animator3X.setInterpolator(new LinearInterpolator());
        animator3Y.setInterpolator(new LinearInterpolator());
        animator3X.start();
        animator3Y.start();

        // Animation number4
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(number4, "translationX", -800f, 850f);
        animator4.setDuration(3500); // 3.5 seconds
        animator4.setRepeatCount(ValueAnimator.INFINITE);
        animator4.setRepeatMode(ValueAnimator.REVERSE);
        animator4.setInterpolator(new LinearInterpolator());
        animator4.start();

        // Animation number5
        ObjectAnimator animator5X = ObjectAnimator.ofFloat(number5, "translationX", 300f, -950f);
        ObjectAnimator animator5Y = ObjectAnimator.ofFloat(number5, "translationY", -850f, 950f);
        animator5X.setDuration(4000); // 4 seconds
        animator5Y.setDuration(4000); // 4 seconds
        animator5X.setRepeatCount(ValueAnimator.INFINITE);
        animator5Y.setRepeatCount(ValueAnimator.INFINITE);
        animator5X.setRepeatMode(ValueAnimator.REVERSE);
        animator5Y.setRepeatMode(ValueAnimator.REVERSE);
        animator5X.setInterpolator(new LinearInterpolator());
        animator5Y.setInterpolator(new LinearInterpolator());
        animator5X.start();
        animator5Y.start();

        // Animation number6
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(number6, "translationY", -900f, 2080f);
        animator6.setDuration(4000); // 4 seconds
        animator6.setRepeatCount(ValueAnimator.INFINITE);
        animator6.setRepeatMode(ValueAnimator.REVERSE);
        animator6.setInterpolator(new LinearInterpolator());
        animator6.start();

        // Animation number7
        ObjectAnimator animator7X = ObjectAnimator.ofFloat(number7, "translationX", -700f, 700f);
        ObjectAnimator animator7Y = ObjectAnimator.ofFloat(number7, "translationY", 800, -900f);
        animator7X.setDuration(2000); // 2 seconds
        animator7Y.setDuration(6000); // 6 seconds
        animator7X.setRepeatCount(ValueAnimator.INFINITE);
        animator7Y.setRepeatCount(ValueAnimator.INFINITE);
        animator7X.setRepeatMode(ValueAnimator.REVERSE);
        animator7Y.setRepeatMode(ValueAnimator.REVERSE);
        animator7X.setInterpolator(new LinearInterpolator());
        animator7Y.setInterpolator(new LinearInterpolator());
        animator7X.start();
        animator7Y.start();

        // Animation number8
        ObjectAnimator animator8X = ObjectAnimator.ofFloat(number8, "translationX", -780f, 950f);
        ObjectAnimator animator8Y = ObjectAnimator.ofFloat(number8, "translationY", 2000f, -800f);
        animator8X.setDuration(5000); // 5 seconds
        animator8Y.setDuration(5000); // 5 seconds
        animator8X.setRepeatCount(ValueAnimator.INFINITE);
        animator8Y.setRepeatCount(ValueAnimator.INFINITE);
        animator8X.setRepeatMode(ValueAnimator.REVERSE);
        animator8Y.setRepeatMode(ValueAnimator.REVERSE);
        animator8X.setInterpolator(new LinearInterpolator());
        animator8Y.setInterpolator(new LinearInterpolator());
        animator8X.start();
        animator8Y.start();

        // Animation number9
        ObjectAnimator animator9 = ObjectAnimator.ofFloat(number9, "translationY", 2000f, -550f);
        animator9.setDuration(4500); // 4.5 seconds
        animator9.setRepeatCount(ValueAnimator.INFINITE);
        animator9.setRepeatMode(ValueAnimator.REVERSE);
        animator9.setInterpolator(new LinearInterpolator());
        animator9.start();

        // Button listeners
        Button buttonStartTheGame = findViewById(R.id.buttonStartTheGame);
        Button buttonAboutTheGame = findViewById(R.id.buttonAboutTheGame);
        Button buttonPreferences = findViewById(R.id.buttonPreferences);

        // Start the game
        Intent i = new Intent(this, Game.class);
        buttonStartTheGame.setOnClickListener(view -> startActivity(i));

        // Show About The Game Fragment when button is clicked
        buttonAboutTheGame.setOnClickListener(v -> aboutTheGameFragment());

        // Show Prefences Fragment when button is clicked
        buttonPreferences.setOnClickListener(v -> preferencesFragment());
    }

    // Shows About The Game Fragment
    private void aboutTheGameFragment() {
        AboutTheGame fragment = new AboutTheGame();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Allows back navigation to remove the fragment
                .commit();

        // Make the fragment container visible
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
    }

    // Shows Preferences Fragment
    private void preferencesFragment() {
        Preferences fragment = new Preferences();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Allows back navigation to remove the fragment
                .commit();

        // Make the fragment container visible
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
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