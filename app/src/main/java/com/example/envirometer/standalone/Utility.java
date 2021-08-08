package com.example.envirometer.standalone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

// This class will hold methods that can be used across all classes
public class Utility {
    private static Snackbar snackbar;
    private static final int ICON_PADDING_SNACKBAR = 55;
    private static int SNACKBAR_DURATION = 450;
    private static final String GREEN = "#478F3D";
    private static final String RED = "#D6392E";
    private static Toast toast;

    public static void displayToast(Context context, String toastMessage) {
        toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 190);
        toast.show();
    }

    public static void showCorrectSnackbar(Activity activity) {
        snackbar = Snackbar.make(activity.findViewById(android.R.id.content), "You have completed a task. Great work!", BaseTransientBottomBar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor(GREEN));

        TextView snackbarText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackbarText.setGravity(Gravity.CENTER);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)snackbarView.getLayoutParams();
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        snackbarView.setLayoutParams(params);

        snackbar.show();
    }
}
