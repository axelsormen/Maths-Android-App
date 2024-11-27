package com.example.mappeinnlevering1s374926;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

public class Preferences extends Fragment {

    private View view;
    private Button buttonBackToMainMenu;
    private Button button5;
    private Button button10;
    private Button button15;
    private ImageButton ukFlag;
    private ImageButton norwegianFlag;
    private ImageButton germanFlag;

    @Override
    public void onResume() {
        super.onResume();
        // Ensure touch_blocker is visible when the fragment is in view
        if (getActivity().findViewById(R.id.touch_blocker) != null) {
            getActivity().findViewById(R.id.touch_blocker).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.preferences, container, false);

        // Ensure touch_blocker is properly visible when fragment is created
        if (getActivity().findViewById(R.id.touch_blocker) != null) {
            getActivity().findViewById(R.id.touch_blocker).setVisibility(View.VISIBLE);
        }

        buttonBackToMainMenu = view.findViewById(R.id.buttonBackToMainMenu);
        button5 = view.findViewById(R.id.button5);
        button10 = view.findViewById(R.id.button10);
        button15 = view.findViewById(R.id.button15);
        ukFlag = view.findViewById(R.id.ukFlag);
        norwegianFlag = view.findViewById(R.id.norwegianFlag);
        germanFlag = view.findViewById(R.id.germanFlag);

        // Load language preference
        SharedPreferences preferences = getActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE);

        // Load and set the current number of problems
        int currentProblems = preferences.getInt("numberOfProblems", 5); // Default to 5 if not set
        updateButtonColors(currentProblems); // Set button colors based on the current preference

        // Handle flag button clicks to change the language
        ukFlag.setOnClickListener(v -> changeLanguage("en", preferences));
        norwegianFlag.setOnClickListener(v -> changeLanguage("no", preferences));
        germanFlag.setOnClickListener(v -> changeLanguage("de", preferences));

        button5.setOnClickListener(v -> changeNumberofProblems(5, preferences));
        button10.setOnClickListener(v -> changeNumberofProblems(10, preferences));
        button15.setOnClickListener(v -> changeNumberofProblems(15, preferences));

        // Handle the buttonBackToMainMenu button click to close the fragment and return to the main menu
        buttonBackToMainMenu.setOnClickListener(v -> closeFragment());

        return view;
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
    }

    private void changeLanguage(String lang, SharedPreferences preferences) {
        // Save language preference
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", lang);
        editor.apply();

        // Set the new locale
        setLocale(lang);

        // Reload the fragment to apply the language change
        reloadFragment();
    }

    private void reloadFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new Preferences());  // Replace with the new fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void changeNumberofProblems(int number, SharedPreferences preferences) {
        // Save the number of problems to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("numberOfProblems", number); // Save the selected number of problems
        editor.apply();

        // Change button colors based on selection
        updateButtonColors(number);
    }

    private void updateButtonColors(int selectedNumber) {
        // Reset button colors to default (you can change this to your default color)
        button5.setBackgroundTintList(getActivity().getColorStateList(R.color.pink));
        button10.setBackgroundTintList(getActivity().getColorStateList(R.color.pink));
        button15.setBackgroundTintList(getActivity().getColorStateList(R.color.pink));

        // Change the selected button's color to green
        switch (selectedNumber) {
            case 5:
                button5.setBackgroundTintList(getActivity().getColorStateList(R.color.brightgreen)); // Use your green color
                break;
            case 10:
                button10.setBackgroundTintList(getActivity().getColorStateList(R.color.brightgreen)); // Use your green color
                break;
            case 15:
                button15.setBackgroundTintList(getActivity().getColorStateList(R.color.brightgreen)); // Use your green color
                break;
        }
    }

    private void closeFragment() {
        // Restart MainActivity when closing Preferences
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish(); // Finish the current activity

        // Optionally, you can remove the fragment instead of hiding it
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(this).commit();

        // Optionally hide the fragment container if needed
        getActivity().findViewById(R.id.fragment_container).setVisibility(View.GONE);

        // Hide touch_blocker when the fragment is removed
        if (getActivity().findViewById(R.id.touch_blocker) != null) {
            getActivity().findViewById(R.id.touch_blocker).setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity().findViewById(R.id.touch_blocker) != null) {
            getActivity().findViewById(R.id.touch_blocker).setVisibility(View.GONE);
        }
    }
}
