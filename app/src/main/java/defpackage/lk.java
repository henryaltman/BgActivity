package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Process;
import com.vi.daemon.DaemonNative;
import com.wolike.ads.AdsHelper;
import com.wolike.ads.AdsJobService;
import com.wolike.ads.AdsLog;
import com.wolike.ads.AdsReceiver;
import com.wolike.ads.ProcessHolder;
import com.wolike.ads.screenmonitor.ScreenMonitorHelper;
import com.wolike.ads.utils.RomUtil;
import defpackage.ok;
import java.io.File;

/* loaded from: classes.dex */
public class lk {
    public kk a;
    public Context b;
    public mk c;
    public ok.Ll1lLl1l1LL1l1Ll d;

    /* loaded from: classes.dex */
    public static final class L1L1L1Ll1l1L1l1l11L {
        @SuppressLint({"StaticFieldLeak"})
        public static lk f3977Ll1lLl1l1LL1l1Ll = new lk(null);
    }

    /* loaded from: classes.dex */
    public class Ll1lLl1l1LL1l1Ll implements Runnable {
        public Ll1lLl1l1LL1l1Ll() {
        }

        @Override // java.lang.Runnable
        public void run() {
            lk lkVar = lk.this;
            lkVar.a(lkVar.b);
        }
    }

    /* loaded from: classes.dex */
    public class l1L1L1L1L1l1LLL1LL1ll implements Runnable {

        /* loaded from: classes.dex */
        public class Ll1lLl1l1LL1l1Ll implements Runnable {
            public Ll1lLl1l1LL1l1Ll(l1L1L1L1L1l1LLL1LL1ll l1l1l1l1l1l1lll1ll1ll) {
            }

            @Override // java.lang.Runnable
            public void run() {
                DaemonNative.restartProcess();
            }
        }

        public l1L1L1L1L1l1LLL1LL1ll() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-2);
            AdsLog.d("startPackageMonitor");
            while (true) {
                try {
                    if ((lk.this.b.getPackageManager().getApplicationInfo(lk.this.b.getPackageName(), 128).flags & 2097152) != 0) {
                        DaemonNative.restartProcess();
                        for (int i2 = 0; i2 < 3; i2++) {
                            new Thread(new Ll1lLl1l1LL1l1Ll(this)).start();
                        }
                    }
                } catch (Throwable th) {
                    AdsLog.e("getApplicationInfo error", th);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class l1l1l1l1LL1L1l1lL1l implements ok.Ll1lLl1l1LL1l1Ll {
        public l1l1l1l1LL1L1l1lL1l() {
        }

        @Override // defpackage.ok.Ll1lLl1l1LL1l1Ll
        public void onScreenMonitorTimer(boolean z) {
        }

        @Override // defpackage.ok.Ll1lLl1l1LL1l1Ll
        public void onScreenStatusChanged(boolean z) {
            if (!RomUtil.isOppo() || z) {
                lk.this.a();
            }
        }
    }

    /* loaded from: classes.dex */
    public class lilil1liLi1lLL1l1l implements Runnable {
        public lilil1liLi1lLL1l1l(lk lkVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public lk(Ll1lLl1l1LL1l1Ll ll1lLl1l1LL1l1Ll) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        new Thread(new lilil1liLi1lLL1l1l(this)).start();
    }

    private void b() {
        for (String str : AdsHelper.getIndicatorFiles(this.b)) {
            if (str != null && new File(str).delete()) {
                AdsLog.d("delete indicatorFile success,file=" + str);
            }
        }
    }

    private void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        this.b.registerReceiver(new AdsReceiver(), intentFilter);
    }

    private boolean d() {
        return RomUtil.isMiui() && Build.VERSION.SDK_INT < 29;
    }

    private void e() {
        if (d()) {
            new Thread(new l1L1L1L1L1l1LLL1LL1ll()).start();
        }
    }

    public static lk getInstance() {
        return L1L1L1Ll1l1L1l1l11L.f3977Ll1lLl1l1LL1l1Ll;
    }

    public mk getCallback() {
        return this.c;
    }

    public kk getConfig() {
        return this.a;
    }

    public Context getContext() {
        return this.b;
    }

    public void init(Context context, kk kkVar, mk mkVar) {
        this.b = context;
        this.a = kkVar;
        this.c = mkVar;
        AdsLog.d("SyncManager DaemonManager init");
        ProcessHolder.init(context);
        AdsHelper.init(context);
        if (ProcessHolder.IS_MAIN) {
            b();
        }
        if (ProcessHolder.IS_MAIN || ProcessHolder.IS_DAEMON) {
            c();
            e();
            new Thread(new Ll1lLl1l1LL1l1Ll()).start();
        }
        AdsHelper.startServices(context);
        if ((ProcessHolder.IS_MAIN || ProcessHolder.IS_SERVICE) && this.a.isScreenMonitorEnable()) {
            if (ProcessHolder.IS_MAIN) {
                ok.getInstance().addCallback(this.d);
            } else {
                ok.setQueryInterval(1000);
            }
            ScreenMonitorHelper.start();
        }
        AdsJobService.scheduleService(context);
    }

    public lk() {
        this.d = new l1l1l1l1LL1L1l1lL1l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        AdsLog.d("forkChild,context=" + context);
        String forkName = AdsHelper.getForkName();
        String selfForkLockFile = AdsHelper.getSelfForkLockFile(context);
        String selfForkWaitFile = AdsHelper.getSelfForkWaitFile(context);
        String selfForkIndicatorFile = AdsHelper.getSelfForkIndicatorFile(context);
        String selfForkWaitIndicatorFile = AdsHelper.getSelfForkWaitIndicatorFile(context);
        AdsLog.d("===============forkChild log start ==============");
        AdsLog.d("forkChild,forkName=" + forkName);
        AdsLog.d("forkChild,forkLockFile=" + selfForkLockFile);
        AdsLog.d("forkChild,forkWaitFile=" + selfForkWaitFile);
        AdsLog.d("forkChild,forkIndicatorFile=" + selfForkIndicatorFile);
        AdsLog.d("forkChild,forkWaitIndicatorFile=" + selfForkWaitIndicatorFile);
        AdsLog.d("===============forkChild log end==============");
        DaemonNative.forkChild(forkName, selfForkLockFile, selfForkWaitFile, selfForkIndicatorFile, selfForkWaitIndicatorFile);
    }
}