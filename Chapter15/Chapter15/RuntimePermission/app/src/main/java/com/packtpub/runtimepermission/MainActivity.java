package com.packtpub.runtimepermission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION_SEND_SMS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean checkPermission(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
        return (permissionCheck == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName},
                permissionRequestCode);
    }

    private void showExplanation(String title, String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                requestPermission(permission, permissionRequestCode);
                            }
                        });
        builder.create().show();
    }

    public void doSomething(View view) {
        if (!checkPermission(Manifest.permission.SEND_SMS)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                showExplanation("Permission Needed", "Rationale",
                        Manifest.permission.SEND_SMS, REQUEST_PERMISSION_SEND_SMS);
            } else {
                requestPermission(Manifest.permission.SEND_SMS,
                        REQUEST_PERMISSION_SEND_SMS);
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Granted!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Denied!", Toast.LENGTH_SHORT)
                            .show();
                }
                return;
            }
        }
    }
}
