package com.wolike.ads.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.example.testso.R;
import com.wolike.ads.AdsLog;
import com.wolike.ads.AdsUtils;
import defpackage.lk;

/* loaded from: classes.dex */
public class JieJingWallpaperService extends WallpaperService {

    /* loaded from: classes.dex */
    public class a extends WallpaperService.Engine {
        private int a() {
            return lk.getInstance().getCallback().getWallPaperPreviewRes();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
            super.onSurfaceChanged(surfaceHolder, i2, i3, i4);
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            super.onSurfaceCreated(surfaceHolder);
            lk.getInstance().getCallback().onWallPaperSurfaceCreated(isPreview());
            Canvas lockCanvas = surfaceHolder.lockCanvas();
            Bitmap a = lockCanvas != null ? a(lockCanvas.getWidth(), lockCanvas.getHeight()) : null;
            if (lockCanvas != null && a != null && !a.isRecycled()) {
                lockCanvas.drawBitmap(Bitmap.createScaledBitmap(a, lockCanvas.getWidth(), lockCanvas.getHeight(), true), 0.0f, 0.0f, new Paint());
                try {
                    surfaceHolder.unlockCanvasAndPost(lockCanvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (lockCanvas != null) {
                AdsLog.d("ResetWallpaperService Bitmap is null");
                lockCanvas.drawColor(-16777216);
                try {
                    surfaceHolder.unlockCanvasAndPost(lockCanvas);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            super.onSurfaceDestroyed(surfaceHolder);
            lk.getInstance().getCallback().onWallPaperSurfaceDestroyed(isPreview());
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public void onVisibilityChanged(boolean z) {
            AdsLog.d("ResetWallpaperService onVisibilityChanged,visible=" + z);
        }

        private Bitmap a(int i2, int i3) {
            if (isPreview()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                try {
                    return BitmapFactory.decodeResource(AdsUtils.getContext().getResources(), a(), options);
                } catch (Throwable unused) {
                    return null;
                }
            } else {
                try {
                    Bitmap bitmap = ((BitmapDrawable) WallpaperManager.getInstance(AdsUtils.getContext()).getDrawable()).getBitmap();
                    if (bitmap != null) {
                        return bitmap;
                    }
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inSampleSize = 1;
                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    try {
                        return BitmapFactory.decodeResource(AdsUtils.getContext().getResources(), R.mipmap.ic_launcher_round, options2);
                    } catch (Throwable unused2) {
                        return null;
                    }
                }
            }
        }
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onCreate() {
        super.onCreate();
        AdsLog.d("CleanWallpaperService onCreate");
    }

    @Override // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        return new a();
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}