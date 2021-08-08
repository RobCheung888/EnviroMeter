package com.example.envirometer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.envirometer.R;
import com.example.envirometer.databinding.ActivityMainBinding;
import com.example.envirometer.header_fragments.HeaderHomeFragment;
import com.example.envirometer.header_fragments.HeaderTasksFragment;
import com.example.envirometer.main_fragments.HomeFragment;
import com.example.envirometer.main_fragments.SubmissionDialog;
import com.example.envirometer.main_fragments.TasksFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Observable;

public class MainActivity extends AppCompatActivity implements SubmissionDialog.SubmissionDialogListener {
    public static final String EXTRA_MESSAGE =
            "com.example.android.environmeter.extra.MESSAGE";
    public static final int EXTRA_REQUEST_CODE = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private Fragment fragmentHeader = null;
    private Fragment fragmentMain = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, new HeaderHomeFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = item -> {
        switch (item.getItemId()) {
            case R.id.home:
                fragmentHeader = new HeaderHomeFragment();
                fragmentMain = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragmentHeader).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMain).commit();
                break;
            case R.id.Tasks:
                fragmentHeader = new HeaderTasksFragment();
                fragmentMain = new TasksFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMain).commit();
                break;
        }
        return true;
    };

    public void openSubmissionDialog(View view) {
        SubmissionDialog submissionDialog = new SubmissionDialog();
        submissionDialog.show(getSupportFragmentManager(), "Submission Dialog");

        // Set target fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment targetFragmentTag = fragmentManager.findFragmentByTag("FRAGMENT_TASKS_TAG");

        submissionDialog.setTargetFragment(targetFragmentTag, 1);
    }

    @Override
    public void applySubmission(String taskName, int amountCompleted, int index) {
        Log.d(LOG_TAG, "Apply submission main");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, TasksFragment.newInstance(taskName, amountCompleted, index), LOG_TAG)
                .commit();
    }
}