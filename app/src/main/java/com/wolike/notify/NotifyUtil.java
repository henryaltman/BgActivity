package com.wolike.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.testso.LitePalApplication;
import com.example.testso.MainActivity;
import com.example.testso.R;
import com.wolike.ads.AdsLog;
import java.io.PrintStream;
import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll;

/* loaded from: classes.dex */
public class NotifyUtil {
    public static final String PERMANENT_CHANNEL_ID = "zbccclean_channel_id";
    public static final int PERMANENT_ID = 102;
    public static final String UPUSH_CHANNEL_ID = "upush_default";
    public static final String a = "NotifyUtil";
    public static String b = "通知";
    public static final int c = 4;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;

    /* renamed from: h  reason: collision with root package name */
    public static final int f3967h = 5;

    /* renamed from: i  reason: collision with root package name */
    public static final int f3968i = 6;
    public static final int j = 7;
    public static final String k = "openflashlight";
    public static final String l = "androidmodel";
    public static NotificationCompat.Builder m = null;
    public static NotificationManagerCompat n = null;
    public static String o = null;
    public static boolean p = false;

    /* renamed from: q  reason: collision with root package name */
    public static RemoteViews f3969q;
    public static Notification r;

    public static NotificationCompat.Builder a(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new NotificationCompat.Builder(context, getPermanentChannelId(context));
        }
        return new NotificationCompat.Builder(context, "");
    }

    public static Notification getNotification(Context context, NotificationCompat.Builder builder, RemoteViews remoteViews) {
        if (Build.VERSION.SDK_INT >= 21) {
            builder.setVisibility(NotificationCompat.VISIBILITY_SECRET);
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return builder.setSmallIcon(R.mipmap.ic_launcher).setCustomContentView(remoteViews).setAutoCancel(false).setShowWhen(false).setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)).setPriority(2).setOngoing(true).setLocalOnly(true).build();
    }

    @RequiresApi(api = 26)
    public static String getPermanentChannelId(Context context) {
        String str = context.getResources().getString(R.string.app_name) + "通知";
        b = str;
        NotificationChannel notificationChannel = new NotificationChannel(PERMANENT_CHANNEL_ID, str, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setSound(null, null);
        n.createNotificationChannel(notificationChannel);
        return notificationChannel.getId();
    }

    public static Notification getPermanentNotification(Context context) {
        AdsLog.d("getPermanentNotification");
        n = NotificationManagerCompat.from(context);
        RemoteViews remoteViews = getRemoteViews(context);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        remoteViews.setOnClickPendingIntent(com.google.android.material.R.layout.notification_template_custom_big, PendingIntent.getActivity(context, 5, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationCompat.Builder a2 = a(context);
        m = a2;
        r = getNotification(context, a2, remoteViews);
        NotificationManagerCompat.from(context).notify(111, a(context).setContentTitle("").setContentText("").setSmallIcon(com.google.android.material.R.drawable.abc_btn_check_to_on_mtrl_000).setAutoCancel(true).setOngoing(true).build());
        NotificationManagerCompat.from(context).cancel(111);
        return r;
    }

    @NonNull
    public static RemoteViews getRemoteViews(Context context) {
        o = Build.MODEL;
        PrintStream printStream = System.out;
        StringBuilder Ll1lLl1l1LL1l1Ll2 = Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll("---------------androidModel:");
        Ll1lLl1l1LL1l1Ll2.append(o);
        printStream.println(Ll1lLl1l1LL1l1Ll2.toString());
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) com.google.android.material.R.layout.notification_template_custom_big);
        f3969q = remoteViews;
        return remoteViews;
    }

    public static Intent go2NLSSettingIntent() {
        try {
            if (Build.VERSION.SDK_INT >= 22) {
                return new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            return new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void sendKeepAliveNoti() {
        Intent intent = new Intent("com.jiejing.clean.keepalive");
        if (Build.VERSION.SDK_INT >= 26) {
            intent.setComponent(new ComponentName(LitePalApplication.getContext(), "com.zbcc.notify.KeepAliveReceiver"));
        }
        LitePalApplication.getContext().sendBroadcast(intent);
    }
}