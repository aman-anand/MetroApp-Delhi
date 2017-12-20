package com.vgroupinc.metroapp.base.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by DELL on 11/13/2017.
 */

public class MyAlert extends AlertDialog {
    private static MyAlert instance;
    private AlertDialog.Builder builder;

    private MyAlert(@NonNull Context context) {
        super(context);
        builder = new Builder(context);
    }

    public static MyAlert getInstance(Context context) {
        if (instance == null) {
            instance = new MyAlert(context);
        }
        return instance;
    }

    public void showError(String message) {
        builder.setMessage(message)
                .setTitle("Error")
                .setCancelable(true)
                .show();
    }

    public void showAlert(String message) {
        builder.setMessage(message)
                .setTitle("Alert")
                .setCancelable(true)
                .show();
    }

}
