package com.wolike.ads;

import android.content.Context;
import defpackage.kk;
import defpackage.lk;
import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.lilil1liLi1lLL1l1l;

/* loaded from: classes.dex */
public class AdsHelperNew {
    public static kk a() {
        boolean z = lilil1liLi1lLL1l1l.f5162Ll1lLl1l1LL1l1Ll;
        kk.Ll1lLl1l1LL1l1Ll newBuilder = kk.newBuilder();
        newBuilder.f3975Ll1lLl1l1LL1l1Ll = z;
        newBuilder.f3976lilil1liLi1lLL1l1l = true;
        return new kk(newBuilder);
    }

    public static void init(Context context) {
        lk.getInstance().init(context, a(), new AdsCallback());
    }
}