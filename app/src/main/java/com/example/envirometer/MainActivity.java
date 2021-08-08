package com.example.envirometer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.envirometer.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SubmissionDialog.SubmissionDialogListener {
    public static final String EXTRA_MESSAGE =
            "com.example.android.environmeter.extra.MESSAGE";
    public static final int EXTRA_REQUEST_CODE = 1;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private Fragment headerFragment = new HeaderHomeFragment();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set up data binding. Google best practice instead of findViewById()
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Retrieve Permissions from user to access gallery
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d(LOG_TAG, "Permission is granted");
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

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

    public void openSubmissionDialog(View view) {
        SubmissionDialog submissionDialog = new SubmissionDialog();
        submissionDialog.show(getSupportFragmentManager(), "Submission Dialog");
    }

    @Override
    public void applySubmission(String taskName, int amountCompleted) {

    }
}