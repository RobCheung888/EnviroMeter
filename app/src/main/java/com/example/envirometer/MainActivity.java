package com.example.envirometer;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.envirometer.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment headerFragment = new HeaderHomeFragment();
    private RecyclerView goalRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goalRecView = findViewById(R.id.goalRecyclerView);
        ArrayList<Target> targets = new ArrayList<>();
        targets.add(new Target("Turn off AC", 12,30));

        TaskRecycleView adapter = new TaskRecycleView(this);
        adapter.setGoal(targets);

        goalRecView.setAdapter(adapter);
        goalRecView.setLayoutManager(new LinearLayoutManager(this));


        // Set up data binding. Google best practice instead of findViewById()
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setOnItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, headerFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = item -> {
        Fragment fragmentHeader = null;
        Fragment fragmentMain = null;

        switch (item.getItemId()) {
            case R.id.home:
                fragmentHeader = new HeaderHomeFragment();
                fragmentMain = new HomeFragment();
                break;
            case R.id.Tasks:
                fragmentHeader = new HeaderTasksFragment();
                fragmentMain = new TasksFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragmentHeader).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMain).commit();
        return true;
    };
}