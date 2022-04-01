package com.wolike.ads.utils;

import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class RomPropDump {
    public static final String PROP_ROM_OS_VERSION_HUAWEI = "ro.build.version.emui";
    public static final String PROP_ROM_OS_VERSION_OPPO = "ro.build.version.opporom";
    public static final String PROP_ROM_OS_VERSION_VIVO = "ro.vivo.os.version";
    public static final String PROP_ROM_OS_VERSION_XIAOMI = "ro.build.version.incremental";
    public Method a;

    private void a() {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    Method method = null;
                    try {
                        method = Build.class.getDeclaredMethod("getString", String.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (method != null) {
                        method.setAccessible(true);
                        this.a = method;
                    }
                }
            }
        }
    }

    public String getRomOs() {
        String str = RomUtil.isHuaWei() ? "ro.build.version.emui" : "";
        if (RomUtil.isMiui()) {
            str = "ro.build.version.incremental";
        }
        if (RomUtil.isOppo()) {
            str = "ro.build.version.opporom";
        }
        if (RomUtil.isVivo()) {
            str = PROP_ROM_OS_VERSION_VIVO;
        }
        String string = getString(str);
        return (TextUtils.isEmpty(string) || "unknown".equals(string)) ? "" : string;
    }

    public String getString(String str) {
        a();
        Method method = this.a;
        if (method == null) {
            return "";
        }
        String str2 = null;
        try {
            str2 = (String) method.invoke(null, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return str2 == null ? "" : str2;
    }
}