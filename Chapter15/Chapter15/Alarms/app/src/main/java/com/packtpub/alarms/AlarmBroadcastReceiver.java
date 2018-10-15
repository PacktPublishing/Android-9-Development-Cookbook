package com.packtpub.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_ALARM= "com.packtpub.alarms.ACTION_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("onReceive()", intent.getAction());
        if (ACTION_ALARM.equals(intent.getAction())) {
            Toast.makeText(context, ACTION_ALARM, Toast.LENGTH_SHORT).show();
        }
    }
}
