package com.example.envirometer.main_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.envirometer.MainActivity;
import com.example.envirometer.R;
import com.example.envirometer.data.TaskRecycleView;
import com.example.envirometer.data.DataTargets;


public class TasksFragment extends Fragment {

    private static final String LOG_TAG = TasksFragment.class.getSimpleName();
    private static final String BUNDLE_TAG_TASK_NAME = "BUNDLE_TAG_TASK_NAME";
    private static final String BUNDLE_TAG_AMOUNT_FILLED = "BUNDLE_TAG_AMOUNT_FILLED";
    private static final String BUNDLE_TAG_INDEX = "BUNDLE_TAG_INDEX";

    private DataTargets dataTargets = new DataTargets();
    private Context context = getContext();
    private TaskRecycleView adapter;

    private String taskName;
    private int amountFilled;
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tasks, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.goalRecyclerView);

        // Set card views
        adapter = new TaskRecycleView(this.getContext());
        adapter.setGoal(dataTargets.getTasks());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void updateCurrentState(String taskName, int amountFilled, int index) {
        Log.d(LOG_TAG, "Change current state");
        adapter.setCurrentState(dataTargets.getTasks(), index, amountFilled);
    }

    // Getter so all fragments and activities can access same data set
    public DataTargets getDataTargets() {
        return dataTargets;
    }
}