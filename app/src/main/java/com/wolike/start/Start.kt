package com.wolike.start

import android.content.Context
import android.content.Intent
import android.util.Log
import com.wolike.start.utils.Utils
import me.weishu.reflection.Reflection

abstract class Start {
    var next: Start? = null
    var delayTime:Long = 500
    abstract fun handle(context: Context, intent: Intent)

    abstract fun satisfy(): Boolean

    fun start(context: Context, intent: Intent, callback: StartCallback? = null) {
        when {
            satisfy() -> {
                handle(context, intent)
                Log.d("OtherController",this.javaClass.simpleName+"__handle")

                Utils.delayInMain(delayTime) {
                    val isStarted = callback?.judgeResult() ?: START_RESULT_FAIL
                    Log.d("FanJava", "${this.javaClass.simpleName} isStarted: $isStarted")
                    if (isStarted == START_RESULT_FAIL) {
                        if (next != null) {
                            next?.start(context, intent, callback)
                        } else { callback?.result(isStarted)
                        }
                    } else {
                        callback?.result(isStarted)
                    }
                }
            }
            next != null -> next?.start(context, intent, callback)
            else -> callback?.result(START_RESULT_FAIL)
        }
    }

    companion object {
        const val TAG = "Start_FanJava"
        const val START_RESULT_SUCCESS = 0
        const val START_RESULT_FAIL = -1
        const val START_RESULT_UNKNOWN = -2

        fun fix(context: Context) {
            Reflection.unseal(context)
        }
    }
}