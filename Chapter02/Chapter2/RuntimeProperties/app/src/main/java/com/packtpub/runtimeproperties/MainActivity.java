package com.packtpub.runtimeproperties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView)findViewById(
                        R.id.textView)).setText("Changed at runtime!");
                LinearLayout.LayoutParams params = (LinearLayout.
                        LayoutParams)view.getLayoutParams();
                params.leftMargin += 5;
            }
        });
    }
}
