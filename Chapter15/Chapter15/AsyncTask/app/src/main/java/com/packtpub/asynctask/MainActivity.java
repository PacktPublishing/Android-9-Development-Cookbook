package com.packtpub.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButtonStart;

    private class CountingTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            int count = params[0];
            for (int x=0;x<count; x++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return count;
        }
        @Override
        protected void onPostExecute(Integer returnVal) {
            super.onPostExecute(returnVal);
            mButtonStart.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonStart=findViewById(R.id.buttonStart);
    }

    public void start(View view){
        mButtonStart.setEnabled(false);
        new CountingTask().execute(10);
    }
}
