package com.wolike.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.wolike.ads.screenmonitor.ScreenMonitorHelper;

/* loaded from: classes.dex */
public class AdsReceiver extends BroadcastReceiver {
    public Handler handler = new Handler(Looper.getMainLooper());

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AdsLog.d("AdsReceiver action=" + action);
        if (action != null) {
            char c = 65535;
            switch (action.hashCode()) {
                case -2128145023:
                    if (action.equals("android.intent.action.SCREEN_OFF")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1454123155:
                    if (action.equals("android.intent.action.SCREEN_ON")) {
                        c = 2;
                        break;
                    }
                    break;
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1947666138:
                    if (action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c != 0) {
                if (c == 1) {
                    this.handler.postDelayed(new Runnable() { // from class: com.wolike.ads.AdsReceiver.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ScreenMonitorHelper.pause();
                        }
                    }, 100L);
                } else if (c == 2) {
                    this.handler.postDelayed(new Runnable() { // from class: com.wolike.ads.AdsReceiver.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ScreenMonitorHelper.resume();
                        }
                    }, 100L);
                }
            }
        }
    }
}