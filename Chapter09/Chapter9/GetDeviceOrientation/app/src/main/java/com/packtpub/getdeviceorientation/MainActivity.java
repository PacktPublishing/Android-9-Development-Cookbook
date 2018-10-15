package com.packtpub.getdeviceorientation;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkOrientation(View view){
        int orientation = getResources()
                .getConfiguration().orientation;
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(MainActivity.this, "ORIENTATION_LANDSCAPE",
                        Toast.LENGTH_SHORT).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(MainActivity.this, "ORIENTATION_PORTRAIT",
                        Toast.LENGTH_SHORT).show();
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                Toast.makeText(MainActivity.this, "ORIENTATION_UNDEFINED",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
