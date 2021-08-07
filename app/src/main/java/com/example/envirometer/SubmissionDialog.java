package com.example.envirometer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SubmissionDialog extends AppCompatDialogFragment {
    private ImageView cancelButton, uploadImageButton, fillButton;

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

        uploadImageButton.setOnClickListener((View.OnClickListener) v -> {
            //TODO: Write code to upload image
        });

        cancelButton.setOnClickListener(v -> getDialog().dismiss());

        fillButton.setOnClickListener((View.OnClickListener) v -> {
            //TODO: Write code to add water
            getDialog().dismiss();
        });

        return builder.create();
    }
}
