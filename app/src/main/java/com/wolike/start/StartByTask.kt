package com.wolike.start

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log


class StartByTask : Start() {
    var isMove = false
    override fun handle(context: Context, intent: Intent) {
        Log.d(TAG, "handle: StartByTask")
        moveToFront(context)
        showRecentTask(context)
        context.startActivity(intent)
    }

    override fun satisfy(): Boolean = true

    private fun moveToFront(context: Context) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        activityManager?.getRunningTasks(50)?.forEach { taskInfo ->
            taskInfo.topActivity?.packageName?.let { Log.e("activityManager", it) };
            try {
                if (taskInfo.topActivity?.packageName == context.packageName) {
                    activityManager.moveTaskToFront(taskInfo.id, 0)
                    activityManager.moveTaskToFront(taskInfo.id, 0)
                    isMove = true
                    return
                }
            } catch (e: Exception) {
            }
        }
    }

    fun showRecentTask(context: Context) {
        if (isMove)return
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        activityManager!!.appTasks
            ?.forEach { taskInfo ->
                taskInfo.moveToFront()
        }
    }
}