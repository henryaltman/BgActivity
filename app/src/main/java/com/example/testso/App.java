package com.example.testso;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.hm.ck.CkActivityStarter;
import com.hm.ck.CkNative;
import com.wolike.ads.AdsHelperNew;
import com.wolike.ads.AdsLog;
import com.wolike.ads.AppContext;
import com.wolike.ads.ProcessHolder;
import com.wolike.ads.intent.ActivityUtils;
import com.wolike.ads.utils.RomUtil;
import com.wolike.notify.NotifyUtil;
import com.wolike.start.Start;
import com.wolike.start.SystemBroadcastReceiver;
import com.wolike.workmanager.WorkManagerUtil;

import l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll.ConstStatus;

public class App extends LitePalApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Start.Companion.fix(this);
        AppContext.set(this);
        ConstStatus.isAppOn = true;
        AdsHelperNew.init(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (ProcessHolder.isMainProcess(this)) {
            NotifyUtil.sendKeepAliveNoti();
//            WorkManagerUtil.startWorker();
//            registerActivityLifecycleCallbacks(new l1l1l1l1LL1L1l1lL1l());
//            ActivityUtils.pop(LitePalApplication.getContext(),new Intent(LitePalApplication.getContext(), MainActivity.class));
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction (Intent.ACTION_PACKAGE_REMOVED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
            intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);


            intentFilter.addAction(Intent.ACTION_SCREEN_ON);
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            intentFilter.addAction(Intent.ACTION_USER_PRESENT);
            intentFilter.addAction(Intent.ACTION_SHUTDOWN);
            intentFilter.addDataScheme("package");

            registerReceiver(new SystemBroadcastReceiver(),intentFilter);
            AdsLog.d("register broadcast11111");
        }

        if (RomUtil.isViVoOppoCk()) {
            CkActivityStarter.initialize(this);
            CkNative.initialize();
        }
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
