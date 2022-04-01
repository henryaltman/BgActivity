package com.hm.ck;

import android.app.Service;
import android.content.Context;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes.dex */
public final class CkNative {
    public static boolean initialized;

    public static native boolean checkService(Context context, Class<? extends Service> cls, String str, boolean z);

    public static native boolean getForegroundState();

    public static native int getLSS();

    public static native int getNaProcess();

    public static native int getProcessPid(int i2);

    public static native void initInfo(String str);

    public static void initialize() {
        if (!initialized) {
            try {
                System.loadLibrary("ck");
            } catch (Throwable unused) {
            }
            initialized = true;
        }
    }

    public static native boolean isRsunlk();

    public static native boolean isScunlk();

    public static native int lockFWithCreate(String str, int i2, int i3);

    public static native void markProcess(int i2);

    public static native void multiFork(int i2, int i3);

    public static native void run(String str, int i2);

    public static native void setForegroundState(boolean z);

    public static native void setRsunlk(boolean z);

    public static native void setScunlk(boolean z);

    public static native void setUpDisable(boolean z);

    public static native String sigHash(Context context);

    public static native void startZ(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, int i3, boolean z);

    public static native void startup(Context context, Class cls, Class cls2);

    public static native void updateLSS(int i2);

    public static native void vdAssistant(Context context);

    public static void initialize(String str) {
        if (!initialized) {
            System.load(str);
            initialized = true;
        }
    }
}