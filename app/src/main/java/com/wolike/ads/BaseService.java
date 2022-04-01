package com.wolike.ads;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class BaseService extends Service {
    public String getMyName() {
        return getClass().getSimpleName();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AdsLog.d(getMyName() + " oncreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AdsLog.d(getMyName() + " onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        AdsLog.d(getMyName() + " onStartCommand");
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        AdsLog.d("onTaskRemoved");
    }
}