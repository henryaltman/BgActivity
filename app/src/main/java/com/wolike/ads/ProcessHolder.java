package com.wolike.ads;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

/* loaded from: classes.dex */
public class ProcessHolder {
    public static boolean IS_DAEMON = false;
    public static boolean IS_MAIN = false;
    public static boolean IS_SERVICE = false;
    public static String PROCESS_NAME = null;
    public static boolean a = false;

    public static String getProcessName(Context context) {
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static void init(Context context) {
        if (!a) {
            String a2 = getProcessName(context);
            String packageName = context.getPackageName();
            PROCESS_NAME = a2;
            if (context.getPackageName().equals(a2)) {
                IS_MAIN = true;
            } else {
                if (a2 != null) {
                    if (a2.equals(packageName + ":daemon")) {
                        IS_DAEMON = true;
                    }
                }
                if (a2 != null) {
                    if (a2.equals(packageName + ":service")) {
                        IS_SERVICE = true;
                    }
                }
            }
            a = true;
        }
    }

    public static boolean isMainProcess(Context context) {
        String a2 = getProcessName(context);
        AdsLog.d("process:"+a2);
        String packageName = context.getPackageName();
        return packageName.equals(a2);
    }
}