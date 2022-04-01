package com.wolike.ads.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class PhoneInfoUtil {
    public static final String PHONE_MODEL_LOCK_SCREEN = "model";
    public static Map<String, String> a = null;
    public static HashMap<String, String> b = null;
    public static final String c = "VersionName";
    public static final String d = "VersionCode";
    public static final String e = "Manufacturer";
    public static final String f = "ProductName";
    public static final String g = "PhoneBrand";

    /* renamed from: h  reason: collision with root package name */
    public static final String f3962h = "PhoneModel";

    /* renamed from: i  reason: collision with root package name */
    public static final String f3963i = "ScreenResolution";
    public static final String j = "RamSize";
    public static final String k = "DeviceName";
    public static final String l = "SDKVersionCode";
    public static final String m = "SDKVersion";
    public static final String n = "brand";
    public static final String o = "version";
    public static final String p = "os";

    public static HashMap<String, String> getCommonPhoneInfos(Context context) {
        if (b == null) {
            b = new LinkedHashMap();
            try {
                if (context.getPackageManager().getPackageInfo(context.getPackageName(), 1) != null) {
                    b.put(n, Build.BRAND);
                    b.put(PHONE_MODEL_LOCK_SCREEN, Build.MODEL);
                    b.put("version", Build.VERSION.RELEASE);
                    HashMap<String, String> hashMap = b;
                    hashMap.put(l, Build.VERSION.SDK_INT + "");
                    b.put("os", new RomPropDump().getRomOs());
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return b;
    }

    public static Map<String, String> getPhoneInfoList(Context context) {
        if (a == null) {
            a = new LinkedHashMap();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(displayMetrics);
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
                if (packageInfo != null) {
                    a.put(c, packageInfo.versionName);
                    Map<String, String> map = a;
                    map.put(d, packageInfo.versionCode + "");
                    a.put(e, Build.MANUFACTURER);
                    a.put(f, Build.PRODUCT);
                    a.put(g, Build.BRAND);
                    a.put(f3962h, Build.MODEL);
                    Map<String, String> map2 = a;
                    map2.put(f3963i, i3 + " x " + i2);
                    Map<String, String> map3 = a;
                    map3.put(j, (memoryInfo.totalMem >> 10) + " KB");
                    a.put(k, Build.DEVICE);
                    Map<String, String> map4 = a;
                    map4.put(l, Build.VERSION.SDK_INT + "");
                    a.put(m, Build.VERSION.RELEASE);
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return a;
    }
}