package com.example.mappeinnlevering1s374926;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class MyDialogue extends DialogFragment {
    private MyInterface callback;

    public interface MyInterface {
        void onYesClick();
        void onNoClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (MyInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("The calling class must implement the interface!");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_return, null);

        // Set up the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        // Initialize elements
        TextView title = dialogView.findViewById(R.id.sureQuitTitle);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button yesButton = dialogView.findViewById(R.id.yesButton);

        // Set the title text
        title.setText(R.string.sureQuit);

        // Set up button click listeners
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNoClick(); // Call onNoClick when cancel is pressed
                dismiss(); // Dismiss the dialog
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onYesClick(); // Call onYesClick when yes is pressed
                dismiss(); // Dismiss the dialog
            }
        });

        return builder.create();
    }
}
