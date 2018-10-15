package com.packtpub.preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String NAME="NAME";
    private EditText mEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String name = sharedPreferences.getString(NAME,null);
        if (name==null) {
            textView.setText("Hello");
        } else {
            textView.setText("Welcome back " + name + "!");
        }
        mEditTextName = findViewById(R.id.editTextName);
    }

    public void saveName(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(NAME, mEditTextName.getText().toString());
        editor.commit();
    }

}
