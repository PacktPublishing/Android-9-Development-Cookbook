package com.packtpub.persistentdata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String KEY_COUNTER = "COUNTER";
    private int mCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        int defaultCounter = 0;
        mCounter = settings.getInt(KEY_COUNTER, defaultCounter);
        ((TextView)findViewById(R.id.textViewCounter))
                .setText("Counter: " + Integer.toString(mCounter));
    }

    public void onClickCounter(View view) {
        mCounter++;
        ((TextView)findViewById(R.id.textViewCounter))
                .setText("Counter: " + Integer.toString(mCounter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, mCounter);
        editor.commit();
    }
}
