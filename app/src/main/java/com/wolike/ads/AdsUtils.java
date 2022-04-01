package com.wolike.ads;

import android.content.Context;
import defpackage.kk;
import defpackage.lk;

/* loaded from: classes.dex */
public class AdsUtils {
    public static kk getConfig() {
        return lk.getInstance().getConfig();
    }

    public static Context getContext() {
        return lk.getInstance().getContext();
    }

    public static boolean isLogEnable() {
        kk config = lk.getInstance().getConfig();
        if (config == null) {
            return false;
        }
        return config.isLogEnable();
    }

    public static boolean isScreenMonitorEnable() {
        return getConfig() != null;
    }
}