package com.packtpub.usingthedefaultcameraapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    final int PHOTO_RESULT=1;
    private Uri mLastPhotoURI=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
    }

    private Uri createFileURI() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(System.currentTimeMillis());
        String fileName = "PHOTO_" + timeStamp + ".jpg";
        return Uri.fromFile(new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName));
    }

    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            mLastPhotoURI = createFileURI();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mLastPhotoURI);
            startActivityForResult(takePictureIntent, PHOTO_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_RESULT && resultCode == RESULT_OK ) {
            AppCompatImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mLastPhotoURI.getPath()));
        }
    }
}
