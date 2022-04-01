package com.wolike.ads.intent;

import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.util.List;

/* loaded from: classes.dex */
public class IntentUtils {
    public static int a;

    public static Intent getLaunchAppIntent(Context context, String str) {
        return context.getPackageManager().getLaunchIntentForPackage(str);
    }

    public static Intent getNcSettingsIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("app_package", context.getPackageName());
        intent.putExtra("app_uid", context.getApplicationInfo().uid);
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        return intent;
    }

    @TargetApi(23)
    public static Intent getWriteSysSettingsIntent(Context context) {
        return new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + context.getPackageName()));
    }

    public static void gotoAirPlaneModeSettings(Context context) {
        startActivitySafe(context, new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
    }

    public static void gotoAppDetail(Context context, String str) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addFlags(FLAG_RECEIVER_FOREGROUND);
        intent.setData(Uri.fromParts("package", str, null));
        startActivitySafe(context, intent);
    }

    public static void gotoNotificationPermission(Context context) {
        startActivitySafe(context, new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    public static void gotoRingtoneSettings(Context context) {
        startActivitySafe(context, new Intent("android.settings.SOUND_SETTINGS"));
    }

    @TargetApi(23)
    public static boolean gotoWriteSysSettings(Context context) {
        return startActivitySafe(context, getWriteSysSettingsIntent(context));
    }

    public static boolean isActivityEnable(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        if (!(intent == null || (queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0)) == null)) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.exported) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startActivitySafe(Context context, Class cls, boolean z) {
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
            return startActivitySafe(context, new Intent(context, cls), z);
        }
        return false;
    }

    public static boolean startCamera(Context context) {
        return startActivitySafe(context, new Intent("android.media.action.STILL_IMAGE_CAMERA"));
    }

    public static boolean startGallery(Context context) {
        return startActivitySafe(context, new Intent("android.intent.action.VIEW", MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
    }

    public static void startHome(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(FLAG_RECEIVER_FOREGROUND);
        startActivitySafe(context, intent);
    }

    public static boolean startPackage(Context context, String str) {
        Intent launchAppIntent = getLaunchAppIntent(context, str);
        if (launchAppIntent != null) {
            return startActivitySafe(context, launchAppIntent);
        }
        return false;
    }

    public static boolean startSoundRecord(Context context) {
        return startActivitySafe(context, new Intent("android.provider.MediaStore.RECORD_SOUND"));
    }

    public static boolean startSysSettings(Context context) {
        return startActivitySafe(context, new Intent("android.settings.SETTINGS"));
    }

    public static boolean startActivitySafe(Context context, Class cls) {
        return startActivitySafe(context, cls, true);
    }

    public static boolean startActivitySafe(Context context, Intent intent) {
        return startActivitySafe(context, intent, true, false);
    }

    public static boolean startActivitySafe(Context context, Intent intent, boolean z) {
        return startActivitySafe(context, intent, z, false);
    }

    public static boolean startActivitySafe(Context context, Intent intent, boolean z, boolean z2) {
        if (context instanceof Activity) {
            try {
                context.startActivity(intent);
                return true;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        } else {
            ActivityUtils.addMiFlags(intent);
            intent.addFlags(FLAG_RECEIVER_FOREGROUND);
            if (z) {
                int i2 = a + 1;
                a = i2;
                if (z2) {
                    try {
                        if (Build.VERSION.SDK_INT >= 29) {
                            ActivityUtilsNew.a(context, intent, 1);
                            return true;
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                try {
                    PendingIntent.getActivity(context, i2, intent, PendingIntent.FLAG_ONE_SHOT).send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                return true;
            }
            try {
                context.startActivity(intent);
                return true;
            } catch (Throwable th3) {
                th3.printStackTrace();
                return false;
            }
        }
    }
}