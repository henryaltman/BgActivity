package com.vi.daemon;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.wolike.ads.AdsLog;
import com.wolike.ads.AdsUtils;
import com.wolike.ads.ViInstrumentation;
import com.wolike.ads.utils.RomUtil;

import java.io.File;

/* loaded from: classes.dex */
public class DaemonNative {
    public static ComponentName sCN = new ComponentName(AdsUtils.getContext(), ViInstrumentation.class);

    static {

        Log.d("sdk", "static initializer: "+System.getProperty("java.library.path") );
        System.loadLibrary("ads");
    }

    public static native void forkChild(String str, String str2, String str3, String str4, String str5);

    public static native int lockFile(String str);

    public static void restartProcess() {
        AdsLog.d("restartProcess");
        Context context = AdsUtils.getContext();
        if (context != null) {
            context.startInstrumentation(sCN, null, null);
        }
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static void setProcessName(String str) {
        if (!RomUtil.isOppo()) {
            try {
                Process.class.getDeclaredMethod("setArgV0", String.class).invoke(null, str);
            } catch (Exception unused) {
                AdsLog.e("setProcessName failed");
            }
        }
    }
}