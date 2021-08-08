package com.example.envirometer.header_fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.envirometer.R;
import com.example.envirometer.data.GetDataTargets;
import com.example.envirometer.main_fragments.HomeFragment;
import com.example.envirometer.standalone.Utility;

public class HeaderHomeFragment extends Fragment {
    private static final String LOG_TAG = HeaderHomeFragment.class.getSimpleName();
    private static int totalPointsWorth = 0;
    private int levelOne = 670;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_header, container, false);
        progressBar = view.findViewById(R.id.home_progressBar);
        setProgressBar();

        return view;
    }

    public void fillUpAmountIfComplete(String taskName, int amountCompleted, int index) {
        // Find total amount needed to complete task
        int totalAmount = Integer.valueOf(GetDataTargets.getDataTargets().getTasks().get(index).getCompleteState());
        int pointsWorth = GetDataTargets.getDataTargets().getTasks().get(index).getPointsWorth();

        if (!isTaskComplete(amountCompleted, totalAmount)) return;

        Log.d(LOG_TAG, String.valueOf(pointsWorth));
        setCompletedAmount(pointsWorth);
        setProgressBar();
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

    public int setProgressBar()
    {
        float percentage = ((float)(totalPointsWorth) / (float)(levelOne)) * 200;
        int percentageInt = Math.round(percentage);
        Log.d(LOG_TAG, "fill up by " + percentage + " %");
        progressBar.setProgress(percentageInt);
        progressBar.setMax(100);
        return 0;
    }

    public static int setCompletedAmount(int pointsWorth)
    {
        totalPointsWorth += pointsWorth;
        return 0;
    }

}