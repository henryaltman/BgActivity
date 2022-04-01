package com.wolike.ads;

import android.app.Instrumentation;
import android.os.Bundle;
import defpackage.lk;
import defpackage.mk;

/* loaded from: classes.dex */
public class ViInstrumentation extends Instrumentation {
    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AdsLog.d("ViInstrumentation onCreate");
        try {
            mk callback = lk.getInstance().getCallback();
            if (callback != null) {
                callback.onInstrumentationCreate();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        super.onDestroy();
        AdsLog.d("ViInstrumentation onDestroy");
    }
}