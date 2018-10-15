package com.packtpub.scopeddirectoryaccess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_FOLDER_MUSIC=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAccessClick(View view) {
        StorageManager storageManager = (StorageManager)getSystemService(Context.STORAGE_SERVICE);
        StorageVolume storageVolume = storageManager.getPrimaryStorageVolume();
        Intent intent = storageVolume.createAccessIntent(Environment.DIRECTORY_MUSIC);
        startActivityForResult(intent, REQUEST_FOLDER_MUSIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_FOLDER_MUSIC:
                if (resultCode == Activity.RESULT_OK) {
                    getContentResolver().takePersistableUriPermission(data.getData(), 0);
                }
                break;
        }
    }
}
