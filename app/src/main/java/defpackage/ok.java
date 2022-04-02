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
    public static int queryInterval = 50;
    public MonitorThread thread;
    public List<screenMonitor> monitorList;

    /* loaded from: classes.dex */
    public interface screenMonitor {
        void onScreenMonitorTimer(boolean z);

        void onScreenStatusChanged(boolean z);
    }

    public static class Holder {

        public static final ok OkInstance = new ok();
    }

    public static class MonitorThread extends Thread {

        public PowerManager powerManager;

        public boolean tempScreenStatus;

        public volatile int screenMonitorStatus = 3;

        public int f3983l1L1L1L1L1l1LLL1LL1ll = 0;

        public MonitorThread() {
            PowerManager powerManager = (PowerManager) AdsUtils.getContext().getSystemService(Context.POWER_SERVICE);
            this.powerManager = powerManager;
            if (powerManager != null) {
                this.tempScreenStatus = powerManager.isScreenOn();
            }
        }

        public synchronized void pauseMonitor() {
            AdsLog.d("ScreenMonitor pauseMonitor,cur status=" + this.screenMonitorStatus);
            if (this.screenMonitorStatus == 1) {
                this.screenMonitorStatus = 2;
                AdsLog.d("ScreenMonitor pauseMonitor success");
            }
        }

        public synchronized void stopMonitor() {
            AdsLog.d("ScreenMonitor stopMonitor,cur status=" + this.screenMonitorStatus);
            if (this.screenMonitorStatus != 3) {
                this.screenMonitorStatus = 3;
                AdsLog.d("ScreenMonitor stopMonitor success");
            }
        }

        public synchronized void startMonitor() {
            AdsLog.d("ScreenMonitor startMonitor,cur status=" + this.screenMonitorStatus);
            if (this.screenMonitorStatus != 1) {
                this.screenMonitorStatus = 1;
                start();
                notify();
                AdsLog.d("ScreenMonitor startMonitor success");
            }
        }

        public synchronized void resumeMonitor() {
            AdsLog.d("ScreenMonitor resumeMonitor,cur status=" + this.screenMonitorStatus);
            if (this.screenMonitorStatus == 2) {
                this.screenMonitorStatus = 1;
                notify();
                AdsLog.d("ScreenMonitor resumeMonitor success");
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.screenMonitorStatus != 3) {
                synchronized (this) {
                    while (this.screenMonitorStatus != 1) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            AdsLog.d("ScreenMonitor wait InterruptedException", e);
                        }
                    }
                }
                boolean isScreenOn = this.powerManager.isScreenOn();
                if (this.tempScreenStatus != isScreenOn) {
                    this.tempScreenStatus = isScreenOn;
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

    public synchronized void a(boolean z) {
        if (this.monitorList != null) {
            for (screenMonitor screenMonitor : this.monitorList) {
                screenMonitor.onScreenStatusChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(boolean z) {
        if (this.monitorList != null) {
            for (screenMonitor screenMonitor : this.monitorList) {
                screenMonitor.onScreenMonitorTimer(z);
            }
        }
    }

    public static ok getInstance() {
        return Holder.OkInstance;
    }

    public static void setQueryInterval(int i2) {
        queryInterval = i2;
    }

    public synchronized void addCallback(screenMonitor screenMonitor) {
        if (this.monitorList == null) {
            this.monitorList = new ArrayList();
        }
        if (!this.monitorList.contains(screenMonitor)) {
            this.monitorList.add(screenMonitor);
        }
    }

    public synchronized void pause() {
        if (this.thread != null) {
            this.thread.pauseMonitor();
        }
    }

    public synchronized void removeCallback(screenMonitor screenMonitor) {
        if (this.monitorList != null) {
            this.monitorList.remove(screenMonitor);
        }
    }

    public synchronized void resume() {
        if (this.thread != null) {
            this.thread.resumeMonitor();
        }
    }

    public synchronized void start() {
        if (this.thread == null || !this.thread.isAlive()) {
            this.thread = new MonitorThread();
        }
        this.thread.startMonitor();
    }

    public synchronized void stop() {
        if (this.thread != null) {
            this.thread.stopMonitor();
        }
    }
}