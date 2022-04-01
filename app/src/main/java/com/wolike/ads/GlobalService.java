package com.wolike.ads;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import androidx.annotation.NonNull;

import com.example.testso.LitePalApplication;
import com.example.testso.MainActivity;
import com.wolike.ads.intent.ActivityUtils;
import com.wolike.ads.intent.IntentUtils;
import com.wolike.ads.utils.RomUtil;
import com.wolike.ads.utils.SafeHandler;
import defpackage.ok;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class GlobalService extends BaseService implements ok.Ll1lLl1l1LL1l1Ll, Handler.Callback {
    public static final String c = "intent";
    public static final String d = "start_activity";
    public static final int e = 1;
    public static final long f = TimeUnit.SECONDS.toMillis(15);
    public MediaPlayer a;
    public Handler b;

    /* loaded from: classes.dex */
    public class a implements MediaPlayer.OnErrorListener {
        public a() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            AdsLog.d(GlobalService.this.getMyName() + " player onError");
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class b implements MediaPlayer.OnCompletionListener {
        public b() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            try {
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GlobalService.this.stopSelf();
        }
    }

    private boolean a() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            return powerManager.isScreenOn();
        }
        return false;
    }

    private void b() {
        try {
            if (this.a == null || !this.a.isPlaying()) {
                c();
            }
        } catch (Throwable unused) {
        }
    }

    private void c() {
        AdsLog.d(getMyName() + " startPlay called");
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception unused) {
                AdsLog.d(getMyName() + " release-1 onError");
            }
        }
        MediaPlayer mediaPlayer2 = new MediaPlayer();
        this.a = mediaPlayer2;
        mediaPlayer2.setOnErrorListener(new a());
        this.a.setWakeMode(getApplicationContext(), 1);
        this.a.setOnCompletionListener(new b());
        try {
            AssetFileDescriptor openFd = getApplicationContext().getAssets().openFd("rubbish.mp3");
            this.a.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.a.setVolume(1.0f, 1.0f);
            if (RomUtil.isHuaWei() && Build.VERSION.SDK_INT >= 21) {
                this.a.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY).build());
            }
            this.a.prepare();
            this.a.start();
            AdsLog.d(getMyName() + " startPlay success");
        } catch (Exception e2) {
            AdsLog.d(getMyName() + " error", e2);
        }
    }

    private void d() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.a.release();
                this.a = null;
            } catch (Exception e2) {
                AdsLog.d(getMyName() + " error", e2);
            } catch (Throwable th) {
                this.a = null;
                throw th;
            }
            this.a = null;
        }
    }

    public static void start(Context context) {
        try {
            context.startService(new Intent(context, GlobalService.class));
        } catch (Exception unused) {
        }
        SecondService.start(context);
    }

    public static void startForLockScreen(Context context, Intent intent) {
        IntentUtils.startActivitySafe(context, intent);
        Intent intent2 = new Intent(context, GlobalService.class);
        if (intent != null) {
            intent2.putExtra(c, intent);
            intent2.setAction(d);
        }
        try {
            context.startService(intent2);
        } catch (Exception e2) {
            AdsLog.d("startService onError", e2);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 1) {
            AdsLog.d(getMyName() + " handleMessage stopPlay");
            d();
        }
        return true;
    }

    @Override // com.wolike.ads.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = new SafeHandler(this);
        ok.getInstance().addCallback(this);
        if (!RomUtil.isMiui()) {
            if (!RomUtil.isOppo() || a()) {
                c();
            }
        }
    }

    @Override // com.wolike.ads.BaseService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ok.getInstance().removeCallback(this);
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e2) {
                AdsLog.d("mPlayer release error", e2);
            }
        }
    }

    @Override // defpackage.ok.Ll1lLl1l1LL1l1Ll
    public void onScreenMonitorTimer(boolean z) {
    }

    @Override // defpackage.ok.Ll1lLl1l1LL1l1Ll
    public void onScreenStatusChanged(boolean z) {
        AdsLog.d(getMyName() + " onScreenStatusChanged->" + z);
        if (z && !RomUtil.isMiui()) {
            this.b.removeMessages(1);
            b();
        } else if (RomUtil.isOppo()) {
            this.b.sendEmptyMessageDelayed(1, f);
        }
    }

    @Override // com.wolike.ads.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Intent intent2;
        if (!(intent == null || !d.equals(intent.getAction()) || (intent2 = (Intent) intent.getParcelableExtra(c)) == null)) {
            boolean isOppo = RomUtil.isOppo();
            if (isOppo) {
                c();
            }
            IntentUtils.startActivitySafe(getApplicationContext(), intent2);
            AdsLog.d(getMyName() + " startActivity,targetIntent=" + intent2);
            if (!isOppo) {
                return Service.START_NOT_STICKY;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new c(), 500);
        }
        return Service.START_NOT_STICKY;
    }
}