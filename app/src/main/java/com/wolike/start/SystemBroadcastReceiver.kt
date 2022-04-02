package com.wolike.start

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
 import com.example.testso.App
import com.example.testso.MainActivity
import com.wolike.ads.AdsLog
import defpackage.lk

class SystemBroadcastReceiver :BroadcastReceiver(){
    private fun startActivity(context: Context,intent: Intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        if (Build.VERSION.SDK_INT <21){
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
            context.startActivity(intent)
            return
        }
        intent.addCategory("android.intent.category.LAUNCHER")
        intent.action = "android.intent.action.MAIN"
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        lk.startActivity(context,intent)
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1== null){
            return
        }
        when (p1.action){
            Intent.ACTION_PACKAGE_ADDED,Intent.ACTION_PACKAGE_REMOVED->{
                AdsLog.d("test"+p1.action)
                startActivity(App.getContext(), Intent(App.getContext(),MainActivity::class.java))
            }
        }
    }
}