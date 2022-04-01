package com.wolike.ads.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class SafeHandler<T extends Handler.Callback> extends Handler {
    public WeakReference<T> a;

    public SafeHandler(T t) {
        super(Looper.getMainLooper());
        this.a = new WeakReference<>(t);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        T t = this.a.get();
        if (t != null) {
            t.handleMessage(message);
        }
    }

    public SafeHandler(T t, Looper looper) {
        super(looper);
        this.a = new WeakReference<>(t);
    }
}