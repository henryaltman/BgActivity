package i.j.a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hm.ck.CkNative;
import com.hm.ck.CkRomUtils;

/* loaded from: classes2.dex */
public class x {
    public static final String a = "e_1_n_2_a_3_b_4_b_5_l_6_e_flag";

    public static void a(Context context, boolean z) {
        try {
            ZS.b(context, a, z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean b(Context context) {
        try {
            if (CkRomUtils.getCurrentROM() == 2) {
                return ZS.a(context, a, false);
            }
            return ZS.a(context, a, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static void c(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new g(context), 1000L);
    }

    public static void a(Context context) {
        CkNative.setUpDisable(!b(context));
    }

    public static void b(Context context, boolean z) {
        try {
            a(context, z);
            CkNative.setUpDisable(!z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}