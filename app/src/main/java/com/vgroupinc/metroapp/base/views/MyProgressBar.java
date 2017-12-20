package com.vgroupinc.metroapp.base.views;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by DELL on 11/13/2017.
 */

public class MyProgressBar {
    private static MyProgressBar barInstance;
    private ProgressDialog progressDialog;

    private MyProgressBar() {
    }

    public static MyProgressBar getProgressDialogInstance() {
        if (barInstance == null) {
            barInstance = new MyProgressBar();
        }
        return barInstance;
    }

    public void showProgress(String message, Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(message);
//        progressDialog.setTitle("Initilizing, Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
