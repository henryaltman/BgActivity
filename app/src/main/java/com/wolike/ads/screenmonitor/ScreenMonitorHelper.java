package com.wolike.ads.screenmonitor;

import com.wolike.ads.AdsUtils;
import defpackage.ok;

/* loaded from: classes.dex */
public class ScreenMonitorHelper {
    public static void pause() {
        ok.getInstance().pause();
    }

    public static void resume() {
        ok.getInstance().resume();
    }

    public static boolean start() {
        if (!AdsUtils.isScreenMonitorEnable()) {
            return false;
        }
        ok.getInstance().start();
        return true;
    }
}