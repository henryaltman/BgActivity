package com.wolike.ads.intent;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.hm.ck.CkActivityStarter;
import com.wolike.ads.AdsLog;
import com.wolike.ads.GlobalService;
import com.wolike.ads.IntentJobService;
import com.wolike.ads.utils.RomUtil;
import java.util.List;

import defpackage.lk;
import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll;

/* loaded from: classes2.dex */
public class ActivityUtils {

    /* loaded from: classes2.dex */
    public static class a implements Runnable {
        public final Context a;
        public final Intent b;

        public a(Context context, Intent intent) {
            this.a = context;
            this.b = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityUtils.b(this.a, this.b);
        }
    }

    /* loaded from: classes2.dex */
    public static class b implements Runnable {
        public final NotificationManagerCompat a;

        public b(NotificationManagerCompat notificationManagerCompat) {
            this.a = notificationManagerCompat;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.cancel(99);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = 26)
    public static String a(Context context) {
        return "intent_channel_id";
    }

    public static void addMiFlags(Intent intent) {
        if (RomUtil.isMiui()) {
            try {
                intent.getClass().getDeclaredMethod("addMiuiFlags", Integer.TYPE).invoke(intent, 2);
            } catch (Exception unused) {
            }
        }
    }

    public static NotificationCompat.Builder b(Context context) {
        return lk.getInstance().getCallback().getIntentNotificationBuilder(context);
    }

    public static void bringAppToForeground(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (!(activityManager == null || (runningTasks = activityManager.getRunningTasks(10)) == null)) {
            for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                ComponentName componentName = runningTaskInfo.topActivity;
                if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                    StringBuilder Ll1lLl1l1LL1l1Ll2 = Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll("bringAppToForeground,taskInfo.topActivity=");
                    Ll1lLl1l1LL1l1Ll2.append(runningTaskInfo.topActivity);
                    Ll1lLl1l1LL1l1Ll2.append(",baseActivity=");
                    Ll1lLl1l1LL1l1Ll2.append(runningTaskInfo.baseActivity);
                    AdsLog.d(Ll1lLl1l1LL1l1Ll2.toString());
                    activityManager.moveTaskToFront(runningTaskInfo.id, 0);
                    return;
                }
            }
        }
    }

    public static void hookJumpActivity(Context context, Intent intent) {
        addMiFlags(intent);
        if (RomUtil.isVivo()) {
            IntentJobService.scheduleService(context, intent, true);
            new Thread(new a(context, intent)).start();
            return;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        NotificationCompat.Builder b2 = b(context);
        b2.setFullScreenIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT), true);
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        try {
            from.cancel(99);
            IntentJobService.scheduleService(context, intent, true);
            IntentUtils.startActivitySafe(context, intent);
            from.notify(99, b2.build());
            new Handler(Looper.getMainLooper()).postDelayed(new b(from), 1000L);
        } catch (Exception e) {
            Log.d("fullScreenIntent", "hookJumpActivity: ", e);
        }
    }

    public static boolean isAppRunningForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null)) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (TextUtils.equals(context.getApplicationInfo().processName, runningAppProcessInfo.processName) && runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void pop(Context context, Intent intent) {
        addMiFlags(intent);
        if (RomUtil.isViVoOppoCk()) {
            CkActivityStarter.queueStartRequest(intent);
            return;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 29) {
            ActivityUtilsNew.a(context, intent, 1);
        }
        NotificationCompat.Builder b2 = b(context);
        b2.setFullScreenIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT), true);
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        try {
            from.cancel(99);
            IntentJobService.scheduleService(context, intent, true);
            IntentUtils.startActivitySafe(context, intent);
            from.notify(99, b2.build());
            new Handler(Looper.getMainLooper()).postDelayed(new b(from), 1000L);
        } catch (Exception e) {
            Log.d("fullScreenIntent", "hookJumpActivity: ", e);
        }
    }

    public static void b(Context context, Intent intent) {
        IntentUtils.startActivitySafe(context, intent);
        boolean isAppRunningForeground = isAppRunningForeground(context);
        if (Build.VERSION.SDK_INT >= 26) {
            if (!isAppRunningForeground) {
                bringAppToForeground(context);
            }
            IntentJobService.scheduleService(context, intent, true);
        }
        boolean isAppRunningForeground2 = isAppRunningForeground(context);
        if (!isAppRunningForeground && isAppRunningForeground2) {
            lk.getInstance().getCallback().moveHomeBack();
        }
        AdsLog.d("vivo,isAppRunningForeground=" + isAppRunningForeground2);
        if (!isAppRunningForeground2) {
            for (int i2 = 0; i2 < 10; i2++) {
                try {
                    bringAppToForeground(context);
                    Thread.sleep(50L);
                    if (isAppRunningForeground(context)) {
                        IntentUtils.startActivitySafe(context, intent);
                        lk.getInstance().getCallback().moveHomeBack();
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            return;
        }
        GlobalService.startForLockScreen(context, intent);
    }
}