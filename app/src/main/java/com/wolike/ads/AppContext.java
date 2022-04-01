package com.wolike.ads;

import android.annotation.SuppressLint;
import android.content.Context;

/* loaded from: classes.dex */
public class AppContext {
    @SuppressLint({"StaticFieldLeak"})
    public static Context a;

    public static Context get() {
        return a;
    }

    public static void set(Context context) {
        a = context;
    }
}