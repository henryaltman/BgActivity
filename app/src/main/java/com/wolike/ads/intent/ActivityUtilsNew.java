package com.wolike.ads.intent;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.wolike.ads.utils.AgooConstants;
import java.util.List;

/* loaded from: classes2.dex */
public class ActivityUtilsNew {
    public static boolean a(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        return context != null && !TextUtils.isEmpty(str) && (runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1)) != null && runningTasks.size() > 0 && str.contains(runningTasks.get(0).topActivity.getClassName());
    }

    public static void b(Context context, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        Intent intent2 = new Intent(intent);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        a(intent2);
        if (Build.VERSION.SDK_INT < 29) {
            a(applicationContext, intent2);
        } else {
            a(applicationContext, intent2, 1);
        }
    }

    public static void a(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(intent);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        if (activity != null) {
            try {
                activity.send();
            } catch (PendingIntent.CanceledException unused) {
            }
        }
        y.a(context, activity);
    }

    public static void a(@NonNull Context context, @NonNull Intent intent, int i2) {
        try {
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(i2, System.currentTimeMillis() + 52, activity);
            }
        } catch (Exception unused) {
        }
    }

    public static void a(Intent intent) {
        if (AgooConstants.MESSAGE_SYSTEM_SOURCE_XIAOMI.equalsIgnoreCase(Build.BRAND) || "redmi".equalsIgnoreCase(Build.BRAND)) {
            try {
                intent.getClass().getDeclaredMethod("addMiuiFlags", Integer.TYPE).invoke(intent, 2);
            } catch (Exception unused) {
            }
        }
    }
}