package com.wolike.ads;

import android.content.Context;
import defpackage.LogConfiguration;
import defpackage.lk;
import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.ConstStatus;

/* loaded from: classes.dex */
public class AdsHelperNew {
    public static LogConfiguration configuration() {
        boolean isLogEnable = ConstStatus.isAppOn;
        LogConfiguration.configurator newBuilder = LogConfiguration.newBuilder();
        newBuilder.isLogEnable = isLogEnable;
        newBuilder.isScreenMonitorEnable = true;
        return new LogConfiguration(newBuilder);
    }

    public static void init(Context context) {
        lk.getInstance().init(context, configuration(), new AdsCallback());
    }
}