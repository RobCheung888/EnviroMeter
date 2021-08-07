package com.example.envirometer;

import android.os.Bundle;
import android.widget.ListAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TasksFragment extends Fragment {
    ArrayList<Target> task = new ArrayList<>();

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        task.add(new Target("Turn off AC", "20","50"));
        task.add(new Target("Turn off AC", "20","50"));
        task.add(new Target("Turn off AC", "20","50"));
        task.add(new Target("Turn off AC", "20","50"));
        task.add(new Target("Turn off AC", "20","50"));
        task.add(new Target("Turn off AC", "20","50"));

        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tasks, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.goalRecyclerView);

        TaskRecycleView adapter = new TaskRecycleView(this.getContext());
        adapter.setGoal(task);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

}