package com.wolike.ads;

import android.content.Context;
import android.content.Intent;
import com.vi.daemon.DaemonNative;
import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll;

/* loaded from: classes.dex */
public class ServiceUtils {
    public static void startService(Context context, Class cls) {
        try {
            context.startService(new Intent(context, cls));
        } catch (Throwable th) {
            StringBuilder Ll1lLl1l1LL1l1Ll2 = Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll("startService error,clz=");
            Ll1lLl1l1LL1l1Ll2.append(cls.getSimpleName());
            AdsLog.e(Ll1lLl1l1LL1l1Ll2.toString());
            if (th instanceof IllegalStateException) {
                DaemonNative.restartProcess();
            }
        }
    }
}