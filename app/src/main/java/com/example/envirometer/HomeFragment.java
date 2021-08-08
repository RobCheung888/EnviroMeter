package com.example.envirometer;

import android.os.Bundle;

import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.envirometer.storage.DataTargets;
import com.example.envirometer.storage.Task;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        int level1 = 300;
        DataTargets goals = new DataTargets();
        TextView volume = view.findViewById(R.id.text_amount_filled);
        volume.setText(currentLevel() + " / " + level1 + "mL");

        return view;

    }

    public int currentLevel()
    {
        //TODO: Add a math formula here to calculate the avg of current water level
        return 0;
    }

}