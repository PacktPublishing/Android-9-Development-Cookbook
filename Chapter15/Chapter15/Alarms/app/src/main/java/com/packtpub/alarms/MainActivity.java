package com.packtpub.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view) {
        Intent intentToFire = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        intentToFire.setAction(AlarmBroadcastReceiver.ACTION_ALARM);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                intentToFire, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long thirtyMinutes=SystemClock.elapsedRealtime() + 30 * 1000;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, thirtyMinutes, alarmIntent);
    }
}
