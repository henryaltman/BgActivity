package com.wolike.ads.wallpaper;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.wolike.ads.intent.IntentUtils;
import com.wolike.ads.utils.RomUtil;

/* loaded from: classes.dex */
public class WallPaperUtils {
    public static void a(Context context) {
        Intent intent = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
        intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", new ComponentName(context, JieJingWallpaperService.class));
        IntentUtils.startActivitySafe(context, intent);
    }

    public static void gotoSetWallPaper(Context context) {
        if (RomUtil.isOppo()) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", new ComponentName(context, JieJingWallpaperService.class));
            intent.setComponent(ComponentName.unflattenFromString("com.android.wallpaper.livepicker/.LiveWallpaperChange"));
            if (IntentUtils.isActivityEnable(context, intent) && IntentUtils.startActivitySafe(context, intent)) {
                return;
            }
        }
        a(context);
    }

    public static boolean isServiceAlive(Context context) {
        WallpaperInfo wallpaperInfo;
        ComponentName component;
        WallpaperManager instance = WallpaperManager.getInstance(context);
        if (instance == null || (wallpaperInfo = instance.getWallpaperInfo()) == null || (component = wallpaperInfo.getComponent()) == null) {
            return false;
        }
        return component.getClassName().equals(JieJingWallpaperService.class.getName());
    }
}