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
import com.example.envirometer.data.DataTargets;
import com.example.envirometer.data.GetDataTargets;
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

    private static final String TAG_HOME = "HOME_TAG";
    private static final String TAG_TASKS = "TAG TASKS";
    private String tag;

    private ActivityMainBinding binding;
    private Fragment fragmentHeader = null;
    private Fragment fragmentMain = null;
    private Fragment fragmentMainTasks = new TasksFragment();
    private Fragment fragmentMainHome = new HomeFragment();

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
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMainHome).commit();
    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = item -> {
        switch (item.getItemId()) {
            case R.id.home:
                fragmentHeader = new HeaderHomeFragment();
                fragmentMain = new HomeFragment();
                tag = TAG_HOME;
                getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragmentHeader).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMainHome, tag).commit();
                break;
            case R.id.Tasks:
                fragmentHeader = new HeaderTasksFragment();
                fragmentMain = new TasksFragment();
                tag = TAG_TASKS;
                getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragmentHeader).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMain, tag).commit();
                break;
        }
/*        getSupportFragmentManager().beginTransaction().replace(R.id.header_container, fragmentHeader, tag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentMain, tag).commit();*/
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
        int currentState = Integer.valueOf(GetDataTargets.getDataTargets().getTasks().get(index).getCurrentState());

        GetDataTargets.getDataTargets().getTasks().get(index).setCurrentState(String.valueOf(amountCompleted + currentState));

        // Update millilitres count
        if (tag.equalsIgnoreCase(TAG_HOME)) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME);
            homeFragment.fillUpAmountIfComplete(taskName, amountCompleted, index);
        }
        else Log.d(LOG_TAG, "Not on home screen");

    }
}