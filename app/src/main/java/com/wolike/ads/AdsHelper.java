package com.wolike.ads;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AdsHelper {
    public static final Map<String, String> a = new HashMap();
    public static final Map<String, String> b = new HashMap();
    public static final Map<String, String> c = new HashMap();
    public static final Map<String, String> d = new HashMap();
    public static final Map<String, String> e = new HashMap();
    public static final Map<String, Class> f = new HashMap();
    public static final Map<String, String> g = new HashMap();

    /* renamed from: h  reason: collision with root package name */
    public static final List<String> f3961h = new ArrayList();

    public static String a(Context context, String str, Map<String, String> map) {
        if (str != null) {
            String str2 = map.get(str);
            File filesDir = context.getFilesDir();
            if (filesDir == null || str2 == null) {
                return null;
            }
            return new File(filesDir, str2).getAbsolutePath();
        }
        throw new IllegalStateException("please init ProcessHolder first");
    }

    public static String getForkName() {
        String str = ProcessHolder.PROCESS_NAME;
        if (str != null) {
            return g.get(str);
        }
        throw new IllegalStateException("please init ProcessHolder first");
    }

    public static List<String> getIndicatorFiles(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : f3961h) {
            arrayList.add(a(context, str, d));
        }
        return arrayList;
    }

    public static String getSelfForkIndicatorFile(Context context) {
        return a(context, d);
    }

    public static String getSelfForkLockFile(Context context) {
        return a(context, b);
    }

    public static String getSelfForkWaitFile(Context context) {
        return a(context, c);
    }

    public static String getSelfForkWaitIndicatorFile(Context context) {
        return a(context, e);
    }

    public static void init(Context context) {
        String packageName = context.getPackageName();
        f3961h.add(packageName);
        f3961h.add(packageName + ":daemon");
        g.put(packageName, "main");
        a.put(packageName, "main");
        b.put(packageName, "main_c");
        c.put(packageName, "daemon_c");
        d.put(packageName, "main_indicator");
        e.put(packageName, "daemon_indicator");
        String str = packageName + ":daemon";
        g.put(str, "daemon");
        a.put(str, "daemon");
        b.put(str, "daemon_c");
        c.put(str, "main_c");
        d.put(str, "daemon_indicator");
        e.put(str, "main_indicator");
        f.put(packageName, GlobalService.class);
        f.put(packageName + ":daemon", AdsService.class);
    }

    public static void startServices(Context context) {
        for (Class cls : f.values()) {
            ServiceUtils.startService(context, cls);
        }
    }

    public static String a(Context context, Map<String, String> map) {
        return a(context, ProcessHolder.PROCESS_NAME, map);
    }
}