package com.wolike.start.utils

import android.os.Handler
import android.os.Looper

object Utils {
    fun delayInMain(delay: Long, cb: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({ cb.invoke() }, delay)
    }
}