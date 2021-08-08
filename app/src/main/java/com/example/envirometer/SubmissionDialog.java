package com.example.envirometer;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import com.example.envirometer.standalone.Utility;
import com.example.envirometer.storage.DataTargets;
import com.example.envirometer.storage.Task;

import java.lang.annotation.Target;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class SubmissionDialog extends AppCompatDialogFragment {
    private static final String LOG_TAG = SubmissionDialog.class.getSimpleName();
    private static final int IMAGE_PICK_CODE = 2;
    private static final int IMAGE_REQUEST_CODE = 1;

    private ImageView cancelButton, uploadImageButton, fillButton, uploadedImageButton;
    private EditText amountCompletedEditText;
    private AutoCompleteTextView dropDownSelectGoal;
    private DataTargets dataTargets = new DataTargets();

    // Listener for when data is submitted
    private SubmissionDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_submission_dialog, null);

        builder.setView(view);

        // Attach fields to layout widgets
        uploadImageButton = view.findViewById(R.id.button_upload);
        uploadedImageButton = view.findViewById(R.id.button_uploaded);
        cancelButton = view.findViewById(R.id.button_cancel);
        fillButton = view.findViewById(R.id.button_fill);
        amountCompletedEditText = view.findViewById(R.id.input_amount_completed);
        AutoCompleteTextView goalsAutoCompleteTextView = view.findViewById(R.id.dropdown_goals_option);

        // Create drop-down menu
        //TODO: Create single dropdown item
        ArrayList<String> tasks = populateArrayWithTasks();
        Log.d(LOG_TAG, dataTargets.getTasks().get(0).toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), R.layout.drop_item, tasks);
        goalsAutoCompleteTextView.setAdapter(adapter);

        // Set upload button to visible and uploaded to invisible
        uploadImageButton.setVisibility(View.VISIBLE);
        uploadedImageButton.setVisibility(View.INVISIBLE);

        uploadImageButton.setOnClickListener((View.OnClickListener) v -> {
            pickImage();
        });

        cancelButton.setOnClickListener(v -> getDialog().dismiss());

        fillButton.setOnClickListener((View.OnClickListener) v -> {
            //TODO: Write code to add water

            // Collect data from user input and adjust other values accordingly
            try {
                // Read amount completed
                int amountCompleted = Integer.valueOf(amountCompletedEditText.getText().toString());
                Log.d(LOG_TAG, "Amount completed: " + amountCompleted);

//                String taskName = goalsAutoCompleteTextView.
                listener.applySubmission(taskName, amountCompleted);
                getDialog().dismiss();
            } catch(NumberFormatException ex) {
                Utility.displayToast(getContext(), "Please enter submission amount");
            }
        });

        return builder.create();
    }

    // Populate Array with tasks from database
    private ArrayList<String> populateArrayWithTasks() {
        ArrayList<String> taskItems = new ArrayList<String>();
        ArrayList<Task> allTasks = dataTargets.getTasks();
        
        int sizeOfArray = allTasks.size();
        for (int i = 0; i < sizeOfArray; i++) {
            taskItems.add(allTasks.get(i).getGoal());
            Log.d(LOG_TAG, allTasks.get(i).toString());
        }
        
        return taskItems;
    }



    private void pickImage() {
        if (ActivityCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Permission not granted so show pop-up
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            // Permission granted so pick image from gallery
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_PICK_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case IMAGE_REQUEST_CODE: {
                // Permission was granted
                if (grantResults.length > 0 & grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickImage();
                else
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //Set uploaded button to visible and upload to invisible
            uploadImageButton.setVisibility(View.INVISIBLE);
            uploadedImageButton.setVisibility(View.VISIBLE);
            return;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            listener = (SubmissionDialogListener) context;
        } catch (Exception e)
        {
            throw new ClassCastException(context.toString() + "Must implement ExampleDialogListener");
        }
    }

    public interface SubmissionDialogListener {
         void applySubmission(String taskName, int amountCompleted);
    }
}
