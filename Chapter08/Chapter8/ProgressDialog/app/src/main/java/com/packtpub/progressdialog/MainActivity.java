package com.packtpub.progressdialog;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mDialog;
    final int THIRTY_SECONDS = 30 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startProgress(View view) {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Doing something...");
        mDialog.setCancelable(false);
        mDialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mDialog.dismiss();
            }
        }, THIRTY_SECONDS);
    }

}
