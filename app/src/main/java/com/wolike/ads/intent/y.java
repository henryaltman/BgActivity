package com.wolike.ads.intent;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.RemoteViews;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.testso.R;
import com.wolike.ads.AppContext;

/* loaded from: classes2.dex */
public class y {
    public static final Handler a = new a();

    /* loaded from: classes2.dex */
    public static class a extends Handler {
        public a() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 101) {
                y.b(AppContext.get());
            }
        }
    }

    @MainThread
    @UiThread
    public static void a(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        NotificationManagerCompat a2 = a(context);
        a(a2);
        a2.notify("tg_a_cf_dua_ppq", 24765, new NotificationCompat.Builder(context, "c.l.n.c.i.d")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setDeleteIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, true)
//                .setCustomHeadsUpContentView(new RemoteViews(context.getPackageName(), (int) R.layout.notification_headsup_layout))
                .build());
        a.sendEmptyMessageDelayed(101, 1000L);
    }

    @MainThread
    @UiThread
    public static void b(@NonNull Context context) {
        a(a(context));
    }

    @NonNull
    public static NotificationManagerCompat a(@NonNull Context context) {
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        if (Build.VERSION.SDK_INT >= 26 && from.getNotificationChannel("c.l.n.c.i.d") == null) {
            NotificationChannel notificationChannel = new NotificationChannel("c.l.n.c.i.d", "安全通知", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLockscreenVisibility(-1);
            notificationChannel.setSound(null, null);
            notificationChannel.enableVibration(false);
            notificationChannel.enableLights(false);
            if (Build.VERSION.SDK_INT >= 29) {
                notificationChannel.setAllowBubbles(false);
            }
            from.createNotificationChannel(notificationChannel);
        }
        return from;
    }

    public static void a(@NonNull NotificationManagerCompat notificationManagerCompat) {
        try {
            notificationManagerCompat.cancel("tg_a_cf_dua_ppq", 24765);
        } catch (Exception unused) {
        }
    }
}