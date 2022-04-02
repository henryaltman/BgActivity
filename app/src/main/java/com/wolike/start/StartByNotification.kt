package com.wolike.start

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log


class StartByNotification : Start() {
    private var manager: NotificationManager? = null
    private var notification: Notification? = null


    override fun handle(context: Context, intent: Intent) {
        Log.d(TAG, "handle: StartByNotification")
        sendNotificationFullScreen(context, intent)
    }

    override fun satisfy(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    fun setNotification(n: Notification) {
        notification = n
    }

    private fun getNotificationManagerManager(context: Context): NotificationManager? {
        if (manager == null) {
            manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        }
        return manager
    }

    private fun sendNotificationFullScreen(
        context: Context,
        intent: Intent
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            clearNotification(context)
            notification?.let {
                val channel = NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH)
                channel.setSound(null, null)
                getNotificationManagerManager(context)?.createNotificationChannel(channel)
                getNotificationManagerManager(context)?.notify(NOTIFICATION_ID, notification)
            }

        }
    }


    private fun clearNotification(context: Context) {
        getNotificationManagerManager(context)?.cancel(NOTIFICATION_ID)
    }


    companion object {
        private const val ID = "channel_out"
        private const val NAME = "notification_out"
        private const val NOTIFICATION_ID: Int = 11112
    }
}