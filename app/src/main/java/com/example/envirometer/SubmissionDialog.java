package com.example.envirometer;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class SubmissionDialog extends AppCompatDialogFragment {
    private static final String LOG_TAG = SubmissionDialog.class.getSimpleName();
    private static final int IMAGE_PICK_CODE = 2;
    private static final int IMAGE_REQUEST_CODE = 1;

    private ImageView cancelButton, uploadImageButton, fillButton;
    private EditText amountCompletedEditText;
    private AutoCompleteTextView dropDownSelectGoal;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_submission_dialog, null);

        builder.setView(view);

        uploadImageButton = view.findViewById(R.id.button_upload);
        cancelButton = view.findViewById(R.id.button_cancel);
        fillButton = view.findViewById(R.id.button_fill);
        amountCompletedEditText = view.findViewById(R.id.input_amount_completed);

        uploadImageButton.setOnClickListener((View.OnClickListener) v -> {
            //TODO: Write code to upload image
            pickImage();
        });

        cancelButton.setOnClickListener(v -> getDialog().dismiss());

        fillButton.setOnClickListener((View.OnClickListener) v -> {
            //TODO: Write code to add water

            //Collect data from user input and adjust other values accordingly
            int amountCompleted = Integer.valueOf(amountCompletedEditText.getText().toString());
            Log.d(LOG_TAG, "Amount completed: " + amountCompleted);

            getDialog().dismiss();
        });

        return builder.create();
    }

    public interface SubmissionDialogListener {
        void applySubmissionAmount(int amountCompleted);
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

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            return;
        }
    }
}
