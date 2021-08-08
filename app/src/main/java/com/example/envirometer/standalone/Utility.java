package com.example.envirometer.standalone;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

// This class will hold methods that can be used across all classes
public class Utility {
    private static Toast toast;

    public static void displayToast(Context context, String toastMessage) {
        toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 190);
        toast.show();
    }
}
