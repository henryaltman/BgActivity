package com.wolike.account;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wolike.ads.AdsLog;

import java.util.ArrayList;
import java.util.HashMap;

public class ExternalStorageProvider extends ContentProvider {
    public static final String[] e = {"root_id", "flags", "icon", "title", "document_id", "available_bytes"};
    public static final String[] f = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size"};
    public HashMap b;
    public HashMap<String, Object> c;
    public ArrayList f5608a;






    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public boolean onCreate() {

        AdsLog.d("ExternalStorageProvider onCreate");

//        if (!Daemon.sInitialized)
//        {
//            new Thread(){
//                @Override
//                public void run() {
//                    try {
//                        sleep(11*1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                        DaemonLog.d("stf:CoreService:ExternStorage");
//                        ContextCompat.startForegroundService(getContext(), new Intent(getContext(), CoreService.class));
//                    }else{
//                        getContext().startService(new Intent(getContext(), CoreService.class));
//                    }
//
//                }
//            }.start();
//        }
        this.f5608a = new ArrayList();
        this.b = new HashMap();
        this.c = new HashMap<>();
        if (!Environment.isExternalStorageEmulated()) {
            return true;
        }
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


}