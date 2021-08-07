package com.example.envirometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.envirometer.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding. Google best practice instead of findViewById()
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setOnItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, new HeaderFragment()).commit();

    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = item -> {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HeaderFragment();
                break;
            case R.id.Tasks:
                fragment = new TasksFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragment).commit();
        return true;
    };
}