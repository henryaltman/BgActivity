package com.wolike.workmanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.testso.LitePalApplication;
import com.example.testso.MainActivity;
import com.wolike.ads.intent.ActivityUtils;

/* loaded from: classes2.dex */
public class AdsWorker extends Worker {
    public AdsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Log.d("AdsWorker", "doWork: ");
        return ListenableWorker.Result.success();
    }
}