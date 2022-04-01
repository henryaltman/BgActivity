package com.wolike.ads;

import android.util.Log;

/* loaded from: classes.dex */
public class AdsLog {
    public static final String a = "ads::";

    public static void d(String str) {
        if (AdsUtils.isLogEnable()) {
            Log.d(a, str);
        }
    }

    public static void e(String str) {
        if (AdsUtils.isLogEnable()) {
            Log.e(a, str);
        }
    }

    public static void d(String str, Throwable th) {
        if (AdsUtils.isLogEnable()) {
            Log.d(a, str, th);
        }
    }

    public static void e(String str, Throwable th) {
        if (AdsUtils.isLogEnable()) {
            Log.e(a, str, th);
        }
    }
}