package com.alexandr.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_FIRST_LAUNCH")) {
            Intent googleRef = new Intent(context, MainActivity.class);
            googleRef.putExtra("initG", "google");
            context.startActivity(googleRef);
        }

    }
}

