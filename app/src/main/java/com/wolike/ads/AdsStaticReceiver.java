package com.wolike.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.testso.LitePalApplication;
import com.example.testso.MainActivity;
import com.wolike.ads.intent.ActivityUtils;

public class AdsStaticReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        AdsLog.d("AdsStaticReceiver onReceive,action=" + action);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.sendBroadcast(new Intent("local_"+action));
    }
}