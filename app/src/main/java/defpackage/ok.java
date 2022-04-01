package defpackage;

import android.content.Context;
import android.os.PowerManager;
import com.wolike.ads.AdsLog;
import com.wolike.ads.AdsUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ok {
    public static final int QUERY_INTERVAL = 50;
    public static final int QUERY_INTERVAL_LARGE = 1000;
    public static int c = 50;
    public lilil1liLi1lLL1l1l a;
    public List<Ll1lLl1l1LL1l1Ll> b;

    /* loaded from: classes.dex */
    public interface Ll1lLl1l1LL1l1Ll {
        void onScreenMonitorTimer(boolean z);

        void onScreenStatusChanged(boolean z);
    }

    /* loaded from: classes.dex */
    public static class l1l1l1l1LL1L1l1lL1l {

        /* renamed from: Ll1lLl1l1LL1l1Ll  reason: collision with root package name */
        public static final ok f3981Ll1lLl1l1LL1l1Ll = new ok();
    }

    /* loaded from: classes.dex */
    public static class lilil1liLi1lLL1l1l extends Thread {

        /* renamed from: Ll1lLl1l1LL1l1Ll  reason: collision with root package name */
        public PowerManager f3982Ll1lLl1l1LL1l1Ll;

        /* renamed from: lilil1liLi1lLL1l1l  reason: collision with root package name */
        public boolean f3985lilil1liLi1lLL1l1l;

        /* renamed from: l1l1l1l1LL1L1l1lL1l  reason: collision with root package name */
        public volatile int f3984l1l1l1l1LL1L1l1lL1l = 3;

        /* renamed from: l1L1L1L1L1l1LLL1LL1ll  reason: collision with root package name */
        public int f3983l1L1L1L1L1l1LLL1LL1ll = 0;

        public lilil1liLi1lLL1l1l() {
            PowerManager powerManager = (PowerManager) AdsUtils.getContext().getSystemService(Context.POWER_SERVICE);
            this.f3982Ll1lLl1l1LL1l1Ll = powerManager;
            if (powerManager != null) {
                this.f3985lilil1liLi1lLL1l1l = powerManager.isScreenOn();
            }
        }

        public synchronized void Ll1lLl1l1LL1l1Ll() {
            AdsLog.d("ScreenMonitor pauseMonitor,cur status=" + this.f3984l1l1l1l1LL1L1l1lL1l);
            if (this.f3984l1l1l1l1LL1L1l1lL1l == 1) {
                this.f3984l1l1l1l1LL1L1l1lL1l = 2;
                AdsLog.d("ScreenMonitor pauseMonitor success");
            }
        }

        public synchronized void l1L1L1L1L1l1LLL1LL1ll() {
            AdsLog.d("ScreenMonitor stopMonitor,cur status=" + this.f3984l1l1l1l1LL1L1l1lL1l);
            if (this.f3984l1l1l1l1LL1L1l1lL1l != 3) {
                this.f3984l1l1l1l1LL1L1l1lL1l = 3;
                AdsLog.d("ScreenMonitor stopMonitor success");
            }
        }

        public synchronized void l1l1l1l1LL1L1l1lL1l() {
            AdsLog.d("ScreenMonitor startMonitor,cur status=" + this.f3984l1l1l1l1LL1L1l1lL1l);
            if (this.f3984l1l1l1l1LL1L1l1lL1l != 1) {
                this.f3984l1l1l1l1LL1L1l1lL1l = 1;
                start();
                notify();
                AdsLog.d("ScreenMonitor startMonitor success");
            }
        }

        public synchronized void lilil1liLi1lLL1l1l() {
            AdsLog.d("ScreenMonitor resumeMonitor,cur status=" + this.f3984l1l1l1l1LL1L1l1lL1l);
            if (this.f3984l1l1l1l1LL1L1l1lL1l == 2) {
                this.f3984l1l1l1l1LL1L1l1lL1l = 1;
                notify();
                AdsLog.d("ScreenMonitor resumeMonitor success");
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f3984l1l1l1l1LL1L1l1lL1l != 3) {
                synchronized (this) {
                    while (this.f3984l1l1l1l1LL1L1l1lL1l != 1) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            AdsLog.d("ScreenMonitor wait InterruptedException", e);
                        }
                    }
                }
                boolean isScreenOn = this.f3982Ll1lLl1l1LL1l1Ll.isScreenOn();
                if (this.f3985lilil1liLi1lLL1l1l != isScreenOn) {
                    this.f3985lilil1liLi1lLL1l1l = isScreenOn;
                    ok.getInstance().a(isScreenOn);
                }
                if (this.f3983l1L1L1L1L1l1LLL1LL1ll >= 100) {
                    ok.getInstance().b(isScreenOn);
                    this.f3983l1L1L1L1L1l1LLL1LL1ll = 0;
                }
                try {
                    Thread.sleep(50L);
                    this.f3983l1L1L1L1L1l1LLL1LL1ll++;
                } catch (InterruptedException e2) {
                    AdsLog.d("ScreenMonitor InterruptedException", e2);
                }
            }
            AdsLog.d("ScreenMonitor status == STATUS_STOPED,exit");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z) {
        if (this.b != null) {
            for (Ll1lLl1l1LL1l1Ll ll1lLl1l1LL1l1Ll : this.b) {
                ll1lLl1l1LL1l1Ll.onScreenStatusChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(boolean z) {
        if (this.b != null) {
            for (Ll1lLl1l1LL1l1Ll ll1lLl1l1LL1l1Ll : this.b) {
                ll1lLl1l1LL1l1Ll.onScreenMonitorTimer(z);
            }
        }
    }

    public static ok getInstance() {
        return l1l1l1l1LL1L1l1lL1l.f3981Ll1lLl1l1LL1l1Ll;
    }

    public static void setQueryInterval(int i2) {
        c = i2;
    }

    public synchronized void addCallback(Ll1lLl1l1LL1l1Ll ll1lLl1l1LL1l1Ll) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (!this.b.contains(ll1lLl1l1LL1l1Ll)) {
            this.b.add(ll1lLl1l1LL1l1Ll);
        }
    }

    public synchronized void pause() {
        if (this.a != null) {
            this.a.Ll1lLl1l1LL1l1Ll();
        }
    }

    public synchronized void removeCallback(Ll1lLl1l1LL1l1Ll ll1lLl1l1LL1l1Ll) {
        if (this.b != null) {
            this.b.remove(ll1lLl1l1LL1l1Ll);
        }
    }

    public synchronized void resume() {
        if (this.a != null) {
            this.a.lilil1liLi1lLL1l1l();
        }
    }

    public synchronized void start() {
        if (this.a == null || !this.a.isAlive()) {
            this.a = new lilil1liLi1lLL1l1l();
        }
        this.a.l1l1l1l1LL1L1l1lL1l();
    }

    public synchronized void stop() {
        if (this.a != null) {
            this.a.l1L1L1L1L1l1LLL1LL1ll();
        }
    }
}