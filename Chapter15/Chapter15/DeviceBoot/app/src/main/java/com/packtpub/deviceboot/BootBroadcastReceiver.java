package com.packtpub.deviceboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context, "BOOT_COMPLETED", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals("<another_action>")) {
            //handle another action
        }
    }
}
