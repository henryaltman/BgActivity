package com.example.testso;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hm.ck.CkActivityStarter;
import com.hm.ck.CkNative;
import com.wolike.ads.AdsHelperNew;
import com.wolike.ads.AppContext;
import com.wolike.ads.ProcessHolder;
import com.wolike.ads.intent.ActivityUtils;
import com.wolike.ads.utils.RomUtil;
import com.wolike.notify.NotifyUtil;
import com.wolike.workmanager.WorkManagerUtil;

import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.lilil1liLi1lLL1l1l;

public class App extends LitePalApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppContext.set(this);
        lilil1liLi1lLL1l1l.f5162Ll1lLl1l1LL1l1Ll = false;
        AdsHelperNew.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (ProcessHolder.IS_MAIN) {
            NotifyUtil.sendKeepAliveNoti();
            WorkManagerUtil.startWorker();
//            registerActivityLifecycleCallbacks(new l1l1l1l1LL1L1l1lL1l());
        }

        if (RomUtil.isViVoOppoCk()) {
            CkActivityStarter.initialize(this);
            CkNative.initialize();
        }
        ActivityUtils.pop(LitePalApplication.getContext(),new Intent(LitePalApplication.getContext(), MainActivity.class));
        startAccountSync(this);
    }

    public static void startAccountSync(Context context){
        String accountType = "com.henrik.keeplive";
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        Account account = null;
        Account[] accounts = accountManager.getAccountsByType(accountType);
        if(accounts.length>0){
            account = accounts[0];
        }else {
            account = new Account("test", accountType);
        }

        if(accountManager.addAccountExplicitly(account,null,null)){
            String authority = accountType;
            long sync_interval = 15*60;
            ContentResolver.setIsSyncable(account,authority,1);
            ContentResolver.setSyncAutomatically(account, authority, true);  //自动同步
            ContentResolver.addPeriodicSync(account, authority, new Bundle(), sync_interval);//设置同步时间间隔
        }
    }
}
