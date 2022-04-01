package i.j.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class ZS {
    public static final SharedPreferences a(Context context) {
        return context.getSharedPreferences("ak_sp", Context.MODE_MULTI_PROCESS);
    }

    public static String b(Context context, String str) {
        try {
            SharedPreferences a = a(context);
            if (a != null) {
                return a.getString(str, null);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static final SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            SharedPreferences a = a(context);
            if (a != null && !TextUtils.isEmpty(str)) {
                SharedPreferences.Editor edit = a.edit();
                edit.putString(str, str2);
                return edit.commit();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(Context context, String str, float f) {
        try {
            SharedPreferences a = a(context);
            if (a == null) {
                return false;
            }
            SharedPreferences.Editor edit = a.edit();
            edit.putFloat(str, f);
            return edit.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static float a(Context context, String str, float f) {
        try {
            SharedPreferences a = a(context);
            return a != null ? a.getFloat(str, f) : f;
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    public static boolean b(Context context, String str, long j) {
        try {
            SharedPreferences a = a(context);
            if (a == null) {
                return false;
            }
            SharedPreferences.Editor edit = a.edit();
            edit.putLong(str, j);
            return edit.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static long a(Context context, String str, long j) {
        try {
            SharedPreferences a = a(context);
            return a != null ? a.getLong(str, j) : j;
        } catch (Throwable th) {
            th.printStackTrace();
            return j;
        }
    }

    public static boolean b(Context context, String str, int i2) {
        try {
            SharedPreferences a = a(context);
            if (a == null) {
                return false;
            }
            SharedPreferences.Editor edit = a.edit();
            edit.putInt(str, i2);
            return edit.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static int a(Context context, String str, int i2) {
        try {
            SharedPreferences a = a(context);
            return a != null ? a.getInt(str, i2) : i2;
        } catch (Throwable th) {
            th.printStackTrace();
            return i2;
        }
    }

    public static boolean a(Context context, String str, boolean z) {
        try {
            SharedPreferences a = a(context);
            return a != null ? a.getBoolean(str, z) : z;
        } catch (Throwable th) {
            th.printStackTrace();
            return z;
        }
    }

    public static boolean b(Context context, String str, boolean z) {
        try {
            SharedPreferences a = a(context);
            if (a == null) {
                return false;
            }
            SharedPreferences.Editor edit = a.edit();
            edit.putBoolean(str, z);
            return edit.commit();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}