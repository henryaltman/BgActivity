package com.wolike.ads.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.hm.ck.CkRomUtils;
import com.wolike.ads.AppContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* loaded from: classes.dex */
public final class RomUtil {
    public static final String A = "ro.build.MiFavor_version";
    public static final String B = "ro.rom.version";
    public static final String C = "ro.build.rom.id";
    public static final String D = "unknown";
    public static RomInfo E = null;
    public static final String HARMONY_OS = "harmony";
    public static final String f183z = "ro.build.uiversion";
    public static int isHarmonyOS = -1;
    public static final String u = "ro.build.version.emui";
    public static final String v = "ro.vivo.os.build.display.id";
    public static final String w = "ro.build.version.incremental";
    public static final String x = "ro.build.version.opporom";
    public static final String y = "ro.letv.release.version";
    public static final String[] a = {"huawei"};
    public static final String[] b = {AgooConstants.MESSAGE_SYSTEM_SOURCE_VIVO};
    public static final String[] c = {AgooConstants.MESSAGE_SYSTEM_SOURCE_XIAOMI};
    public static final String[] d = {AgooConstants.MESSAGE_SYSTEM_SOURCE_OPPO};
    public static final String[] e = {"leeco", "letv"};
    public static final String[] f = {"360", "qiku"};
    public static final String[] g = {"zte"};

    /* renamed from: h  reason: collision with root package name */
    public static final String[] f3964h = {"oneplus"};

    /* renamed from: i  reason: collision with root package name */
    public static final String[] f3965i = {"nubia"};
    public static final String[] j = {"coolpad", "yulong"};
    public static final String[] k = {"lg", "lge"};
    public static final String[] l = {"google"};
    public static final String[] m = {"samsung"};
    public static final String[] n = {"meizu"};
    public static final String[] o = {"lenovo"};
    public static final String[] p = {"smartisan"};

    /* renamed from: q  reason: collision with root package name */
    public static final String[] f3966q = {"htc"};
    public static final String[] r = {"sony"};
    public static final String[] s = {ADDef.AD_AgentName_Gionee, "amigo"};
    public static final String[] t = {"motorola"};

    /* loaded from: classes.dex */
    public static class RomInfo {
        public String a;
        public String b;

        public String getName() {
            return this.a;
        }

        public String getVersion() {
            return this.b;
        }


    }

    public RomUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public static String b() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static String c(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(String str) {
        return "";
    }

    public static String e(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getCameraFolderName() {
        return isHtc() ? "100MEDIA" : "Camera";
    }

    public static RomInfo getRomInfo() {
        RomInfo romInfo = E;
        if (romInfo != null) {
            return romInfo;
        }
        E = new RomInfo();
        String a2 = a();
        String b2 = b();
        if (a(a2, b2, a)) {
            E.a = a[0];
            String a3 = a("ro.build.version.emui");
            String[] split = a3.split("_");
            if (split.length > 1) {
                E.b = split[1];
            } else {
                E.b = a3;
            }
            return E;
        } else if (a(a2, b2, b)) {
            RomInfo romInfo2 = E;
            romInfo2.a = b[0];
            romInfo2.b = a("ro.vivo.os.build.display.id");
            return E;
        } else if (a(a2, b2, c)) {
            RomInfo romInfo3 = E;
            romInfo3.a = c[0];
            romInfo3.b = a("ro.build.version.incremental");
            return E;
        } else if (a(a2, b2, d)) {
            RomInfo romInfo4 = E;
            romInfo4.a = d[0];
            romInfo4.b = a("ro.build.version.opporom");
            return E;
        } else if (a(a2, b2, e)) {
            RomInfo romInfo5 = E;
            romInfo5.a = e[0];
            romInfo5.b = a("ro.letv.release.version");
            return E;
        } else if (a(a2, b2, f)) {
            RomInfo romInfo6 = E;
            romInfo6.a = f[0];
            romInfo6.b = a("ro.build.uiversion");
            return E;
        } else if (a(a2, b2, g)) {
            RomInfo romInfo7 = E;
            romInfo7.a = g[0];
            romInfo7.b = a("ro.build.MiFavor_version");
            return E;
        } else if (a(a2, b2, f3964h)) {
            RomInfo romInfo8 = E;
            romInfo8.a = f3964h[0];
            romInfo8.b = a("ro.rom.version");
            return E;
        } else if (a(a2, b2, f3965i)) {
            RomInfo romInfo9 = E;
            romInfo9.a = f3965i[0];
            romInfo9.b = a("ro.build.rom.id");
            return E;
        } else {
            if (a(a2, b2, j)) {
                E.a = j[0];
            } else if (a(a2, b2, k)) {
                E.a = k[0];
            } else if (a(a2, b2, l)) {
                E.a = l[0];
            } else if (a(a2, b2, m)) {
                E.a = m[0];
            } else if (a(a2, b2, n)) {
                E.a = n[0];
            } else if (a(a2, b2, o)) {
                E.a = o[0];
            } else if (a(a2, b2, p)) {
                E.a = p[0];
            } else if (a(a2, b2, f3966q)) {
                E.a = f3966q[0];
            } else if (a(a2, b2, r)) {
                E.a = r[0];
            } else if (a(a2, b2, s)) {
                E.a = s[0];
            } else if (a(a2, b2, t)) {
                E.a = t[0];
            } else {
                E.a = b2;
            }
            E.b = a("");
            return E;
        }
    }

    public static boolean isCoolpad() {
        return j[0].equals(getRomInfo().a);
    }

    public static boolean isFlyme() {
        return n[0].equals(getRomInfo().a);
    }

    public static boolean isGionee() {
        return s[0].equals(getRomInfo().a);
    }

    public static boolean isGoogle() {
        return l[0].equals(getRomInfo().a);
    }

    public static boolean isHarmonyOS() {
        int i2 = isHarmonyOS;
        if (i2 != -1) {
            return i2 == 1;
        }
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            if (HARMONY_OS.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]))) {
                isHarmonyOS = 1;
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            Class.forName("ohos.aafwk.ability.Ability");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setPackage("com.huawei.systemmanager");
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.puremode.PureModeActivity"));
            if (AppContext.get().getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                isHarmonyOS = 1;
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        isHarmonyOS = 0;
        return false;
    }

    public static boolean isHtc() {
        return f3966q[0].equals(getRomInfo().a);
    }

    public static boolean isHuaWei() {
        return a[0].equals(getRomInfo().a) || isHuaweiBrand();
    }

    public static boolean isHuaweiBrand() {
        String str = Build.MANUFACTURER;
        return str != null && (str.equalsIgnoreCase("HUAWEI") || str.equalsIgnoreCase("HONOR"));
    }

    public static boolean isLeeco() {
        return e[0].equals(getRomInfo().a);
    }

    public static boolean isLenovo() {
        return o[0].equals(getRomInfo().a);
    }

    public static boolean isLg() {
        return k[0].equals(getRomInfo().a);
    }

    public static boolean isMiui() {
        return c[0].equals(getRomInfo().a);
    }

    public static boolean isMotorola() {
        return t[0].equals(getRomInfo().a);
    }

    public static boolean isNubia() {
        return f3965i[0].equals(getRomInfo().a);
    }

    public static boolean isOneplus() {
        return f3964h[0].equals(getRomInfo().a);
    }

    public static boolean isOppo() {
        return d[0].equals(getRomInfo().a);
    }

    public static boolean isQiku() {
        return f[0].equals(getRomInfo().a);
    }

    public static boolean isSamsung() {
        return m[0].equals(getRomInfo().a);
    }

    public static boolean isSmartisan() {
        return p[0].equals(getRomInfo().a);
    }

    public static boolean isSony() {
        return r[0].equals(getRomInfo().a);
    }

    public static boolean isViVoOppoCk() {
        int i2;
        if (isVivo() || CkRomUtils.getCurrentROM() == 4) {
            return true;
        }
        return isOppo() && (i2 = Build.VERSION.SDK_INT) < 28 && i2 > 21;
    }

    public static boolean isVivo() {
        return b[0].equals(getRomInfo().a);
    }

    public static boolean isZte() {
        return g[0].equals(getRomInfo().a);
    }

    public static String a() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static String b(String str) {
        String d2 = d(str);
        if (!TextUtils.isEmpty(d2)) {
            return d2;
        }
        String e2 = e(str);
        return (!TextUtils.isEmpty(e2) || Build.VERSION.SDK_INT >= 28) ? e2 : c(str);
    }

    public static String a(String str) {
        String b2 = !TextUtils.isEmpty(str) ? b(str) : "";
        if (TextUtils.isEmpty(b2) || b2.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    b2 = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        return TextUtils.isEmpty(b2) ? "unknown" : b2;
    }
}