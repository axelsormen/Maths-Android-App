package com.example.mappeinnlevering1s374926;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AboutTheGame extends Fragment {

    // Define needed variables
    View view;
    Button buttonBackToMainMenu3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_the_game, container, false);

        buttonBackToMainMenu3 = view.findViewById(R.id.buttonBackToMainMenu3);

        // Activate touch blocker
        getActivity().findViewById(R.id.touch_blocker).setVisibility(View.VISIBLE);

        buttonBackToMainMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove fragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(AboutTheGame.this).commit();
                getActivity().findViewById(R.id.fragment_container).setVisibility(View.GONE);

                // Deactivate touch blocker
                getActivity().findViewById(R.id.touch_blocker).setVisibility(View.GONE);
            }
        });

        return view;
    }
}
