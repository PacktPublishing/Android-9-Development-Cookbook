package com.packtpub.runtimewidget;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        //ConstraintLayout layout = findViewById(R.id.layout);
        
        ConstraintLayout layout = new ConstraintLayout(this);
        DatePicker datePicker = new DatePicker(this);
        layout.addView(datePicker);
        setContentView(layout);
    }
}
