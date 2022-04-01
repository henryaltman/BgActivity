package com.wolike.workmanager;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.testso.LitePalApplication;

import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class WorkManagerUtil {
    public static final String TAG = "WorkManagerWork";

    public static void startWorker() {
        try {
            WorkManager.getInstance(LitePalApplication.getContext()).enqueue(new PeriodicWorkRequest.Builder(AdsWorker.class, 20L, TimeUnit.SECONDS).build());
        } catch (Exception unused) {
        }
    }
}