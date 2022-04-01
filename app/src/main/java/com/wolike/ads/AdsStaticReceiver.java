package com.wolike.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class AdsStaticReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        AdsLog.d("AdsStaticReceiver onReceive,action=" + action);

    }
}