package com.wolike.ads;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.testso.R;
import com.wolike.ads.utils.PhoneInfoUtil;
import com.wolike.ads.utils.RomUtil;
import defpackage.CommonCallback;


/* loaded from: classes.dex */
public class AdsCallback implements CommonCallback {
    private static final String TAG = "AdsCallback";
    public static final String a = "com.jiejing.clean.brandnew";

    @Override
    @TargetApi(26)
    public String getIntentChannelId(Context context) {
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        NotificationChannel notificationChannel = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(false);
        notificationChannel.setShowBadge(false);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        notificationChannel.setLockscreenVisibility(-1);
        notificationChannel.setBypassDnd(true);
        from.createNotificationChannel(notificationChannel);
        return notificationChannel.getId();
    }

    @Override
    public NotificationCompat.Builder getIntentNotificationBuilder(Context context) {
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new NotificationCompat.Builder(AppContext.get(), getIntentChannelId(context));
        } else {
            builder = new NotificationCompat.Builder(AppContext.get(),(String) null );
        }
        builder.setContentTitle("手机优化中");
        builder.setContentText("正在优化您的手机");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setDefaults(4);
        builder.setPriority(-1);
        return builder;
    }

    @Override // defpackage.mk
    public String getSyncAccountName() {
        return "test";
    }

    @Override // defpackage.mk
    public String getSyncAccountType() {
        return "com.henrik.keeplive";
    }

    @Override // defpackage.mk
    public int getWallPaperPreviewRes() {
        if (RomUtil.isHuaWei()) {
            if (Build.VERSION.SDK_INT == 27 && !"JSN-AL00".equals(PhoneInfoUtil.getCommonPhoneInfos(AppContext.get()).get(PhoneInfoUtil.PHONE_MODEL_LOCK_SCREEN))) {
                return R.mipmap.ic_launcher_round;
            }
        } else if (RomUtil.isMiui()) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 == 28 || i2 == 27) {
                return R.mipmap.ic_launcher_round;
            }
        } else if (RomUtil.isSamsung()) {
            return R.mipmap.ic_launcher_round;
        }
        return R.mipmap.ic_launcher;
    }

    @Override // defpackage.mk
    public void moveHomeBack() {
        Log.d(TAG, "moveHomeBack: ");
    }

    @Override // defpackage.mk
    public void onInstrumentationCreate() {
    }

    @Override // defpackage.mk
    public void onWallPaperSurfaceCreated(boolean z) {
        AdsLog.d("ResetWallpaperService onSurfaceCreated");
        if (!z) {
            AdsLog.d("Wallpaper is not Preview");
            return;
        }
        AdsLog.d("Wallpaper is Preview");
    }

    @Override // defpackage.mk
    public void onWallPaperSurfaceDestroyed(boolean z) {
        if (z && RomUtil.isOppo()) {
            Log.d(TAG, "onWallPaperSurfaceDestroyed: onWallPaperSurfaceDestroyed");
        }
    }
}