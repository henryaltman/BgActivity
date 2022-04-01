package com.hm.ck;

import android.net.LocalServerSocket;
import android.os.Process;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes.dex */
public final class CkProcessHolder {
    public static final Object locker = new Object();
    public static LocalServerSocket myLss;

    @Keep
    public static boolean lock() {
        synchronized (locker) {
            if (myLss != null) {
                return true;
            }
            try {
                myLss = new LocalServerSocket("akm2" + Process.myPid());
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    @Keep
    public static void release() {
        synchronized (locker) {
            if (myLss != null) {
                try {
                    myLss.close();
                } catch (Exception unused) {
                }
                myLss = null;
            }
        }
    }

    @Keep
    public static boolean touch(int i2) {
        try {
            new LocalServerSocket(String.format("akm2%s", Integer.valueOf(i2))).close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}