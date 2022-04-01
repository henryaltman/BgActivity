package com.example.testso;

import android.app.Application;
import android.content.Context;

/* loaded from: classes.dex */
public class LitePalApplication extends Application {

    /* renamed from: Ll1lLl1l1LL1l1Ll  reason: collision with root package name */
    public static Context f8474Ll1lLl1l1LL1l1Ll;

    public LitePalApplication() {
        f8474Ll1lLl1l1LL1l1Ll = this;
    }

    public static Context getContext() {
        Context context = f8474Ll1lLl1l1LL1l1Ll;
        if (context != null) {
            return context;
        }
        throw new GlobalException(GlobalException.APPLICATION_CONTEXT_IS_NULL);
    }
}