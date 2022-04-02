package com.wolike.start

import android.content.Context
import android.content.Intent
import android.os.Build
import me.weishu.reflection.Reflection

class StartByHookMi : Start() {
    override fun handle(context: Context, intent: Intent) {
        reflect(intent)
        context.startActivity(intent)
    }

    override fun satisfy(): Boolean = "Xiaomi".equals(Build.MANUFACTURER, ignoreCase = true)

    private fun reflect(intent: Intent) {
        try {
            val method = intent.javaClass
                .getDeclaredMethod("addMiuiFlags", Int::class.javaPrimitiveType)
            method.invoke(intent, 0x2)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun fix(context: Context) {
            Reflection.unseal(context)
        }
    }
}