package com.example.envirometer.main_fragments;

import android.os.Bundle;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.envirometer.R;
import com.example.envirometer.data.GetDataTargets;
import com.example.envirometer.standalone.Utility;

import me.itangqi.waveloadingview.WaveLoadingView;

public class HomeFragment extends Fragment {
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private static int totalPointsWorth = 0;

    private TextView volume;
    private WaveLoadingView waveLoadingView;
    private int levelOne = 670;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        volume = view.findViewById(R.id.text_amount_filled);
        waveLoadingView = view.findViewById(R.id.waveLoadingView);

        volume.setText(totalPointsWorth + "mL" + " / " + levelOne + "mL");
        setWaveLoadingView();

        return view;

    }

    public void fillUpAmountIfComplete(String taskName, int amountCompleted, int index) {
        // Find total amount needed to complete task
        int totalAmount = Integer.valueOf(GetDataTargets.getDataTargets().getTasks().get(index).getCompleteState());
        int pointsWorth = GetDataTargets.getDataTargets().getTasks().get(index).getPointsWorth();

        if (!isTaskComplete(amountCompleted, totalAmount)) return;

        Log.d(LOG_TAG, String.valueOf(pointsWorth));
        setCompletedAmount(pointsWorth);
        setWaveLoadingView();
        volume.setText(totalPointsWorth + "mL" + " / " + levelOne + "mL");
    }

    private boolean isTaskComplete(int amountCompleted, int totalAmount) {
        if (amountCompleted >= totalAmount) {
            Log.d(LOG_TAG, "Full");
            Utility.showCorrectSnackbar(getActivity());
            return true;
        }
        else {
            Log.d(LOG_TAG, "Not full");
            return false;
        }
    }

    public int setWaveLoadingView()
    {
        //TODO: Add a math formula here to calculate the avg of current water level
        float percentage = ((float)(totalPointsWorth) / (float)(levelOne)) * 250;
        int percentageInt = Math.round(percentage);
        Log.d(LOG_TAG, "fill up by " + percentageInt + " %");
        waveLoadingView.setProgressValue(percentageInt);
        return 0;
    }

    public static int setCompletedAmount(int pointsWorth)
    {
        totalPointsWorth += pointsWorth;
        return 0;
    }

}