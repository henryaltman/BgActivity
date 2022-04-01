package com.wolike.ads;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class SecondService extends BaseService {
    public static void start(Context context) {
        try {
            context.startService(new Intent(context, SecondService.class));
        } catch (Exception unused) {
        }
    }

    @Override // com.wolike.ads.BaseService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}