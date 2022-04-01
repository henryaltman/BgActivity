package com.wolike.ads;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.wolike.ads.utils.RomUtil;
import com.wolike.ads.wallpaper.WallPaperUtils;
import java.util.concurrent.TimeUnit;

@TargetApi(21)
/* loaded from: classes.dex */
public class AdsJobService extends JobService {
    public static final int a = 1000;
    public static final long b = TimeUnit.MINUTES.toMillis(1);
    public static final long c = TimeUnit.MINUTES.toMillis(5);
    public static long time = 0;

    public static void scheduleService(Context context) {
        if (WallPaperUtils.isServiceAlive(context)) {
            AdsLog.d("WallPaperUtils isServiceAlive,return");
            return;
        }
        AdsLog.d("AdsJobService scheduleService");
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            JobInfo.Builder persisted = new JobInfo.Builder(1000, new ComponentName(context, AdsJobService.class)).setPersisted(true);
            if (RomUtil.isMiui()) {
                time = c;
            } else {
                time = b;
            }
            if (Build.VERSION.SDK_INT < 24) {
                persisted.setPeriodic(time);
            } else {
                persisted.setMinimumLatency(time);
            }
            try {
                jobScheduler.schedule(persisted.build());
            } catch (Throwable unused) {
            }
        }
    }

    public static void stopScheduleService(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.cancel(1000);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AdsLog.d("AdsJobService onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AdsLog.d("AdsJobService onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        AdsLog.d("AdsJobService onStartCommand");
        return Service.START_NOT_STICKY;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        AdsLog.d("AdsJobService onStartJob");
        if (Build.VERSION.SDK_INT >= 24) {
            scheduleService(getApplicationContext());
        }
        GlobalService.start(getApplicationContext());
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        AdsLog.d("AdsJobService onStopJob");
        return false;
    }
}