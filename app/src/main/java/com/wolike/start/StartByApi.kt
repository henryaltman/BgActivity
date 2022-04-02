package com.wolike.start

import android.content.Context
import android.content.Intent
import android.util.Log
import com.wolike.start.Start


class StartByApi : Start() {
    override fun handle(context: Context, intent: Intent) {
        Log.d(TAG, "handle: ")
        context.startActivity(intent)
    }

    override fun satisfy(): Boolean = true
}