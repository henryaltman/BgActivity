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
import android.os.Bundle;
import com.wolike.ads.intent.IntentUtils;

@TargetApi(21)
/* loaded from: classes2.dex */
public class IntentJobService extends JobService {
    public static final String a = "is_activity";
    public static final int b = 1001;

    public static void scheduleService(Context context, Intent intent, boolean z) {
        AdsLog.d("IntentJobService scheduleService");
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            JobInfo.Builder persisted = new JobInfo.Builder(1001, new ComponentName(context, IntentJobService.class)).setPersisted(false);
            if (Build.VERSION.SDK_INT >= 28) {
                persisted.setImportantWhileForeground(true);
            }
            persisted.setRequiresDeviceIdle(false);
            persisted.setOverrideDeadline(3000L);
            if (Build.VERSION.SDK_INT >= 26 && intent != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.intent.extra.INTENT", intent);
                bundle.putBoolean(a, z);
                persisted.setTransientExtras(bundle);
            }
            try {
                jobScheduler.schedule(persisted.build());
            } catch (Throwable th) {
                AdsLog.d("IntentJobService schedule error", th);
            }
        }
    }

    public static void stopScheduleService(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.cancel(1001);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AdsLog.d("IntentJobService onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AdsLog.d("IntentJobService onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        AdsLog.d("IntentJobService onStartCommand");
        return Service.START_NOT_STICKY;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        AdsLog.d("IntentJobService onStartJob");
        if (Build.VERSION.SDK_INT >= 26) {
            Bundle transientExtras = jobParameters.getTransientExtras();
            Intent intent = (Intent) transientExtras.getParcelable("android.intent.extra.INTENT");
            AdsLog.d("IntentJobService onStartJob intent=" + intent);
            if (intent != null) {
                if (transientExtras.getBoolean(a)) {
                    IntentUtils.startActivitySafe((Context) this, intent, false);
                } else {
                    try {
                        startService(intent);
                    } catch (Exception e) {
                        AdsLog.d("IntentJobService start service error", e);
                    }
                }
            }
        }
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        AdsLog.d("IntentJobService onStopJob");
        return false;
    }
}