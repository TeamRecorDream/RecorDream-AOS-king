package com.recodream_aos.recordream.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.recodream_aos.recordream.R
import com.recodream_aos.recordream.presentation.MainActivity

class RecorDreamFireBaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("fcm", "onNewToken: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data[TITLE] ?: ""
            val body = remoteMessage.data[BODY] ?: ""
            sendNotification(title, body)
        }
    }

    private fun sendNotification(title: String, body: String) {
        createNotificationChannel()
        val intent = Intent(this, MainActivity::class.java)
//        val intent = Intent(this, LoginActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setContentText(body)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel =
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT,
            )

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "recordDream_channel"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_NAME = "recordDream_channel_name"
        const val TITLE = "title"
        const val BODY = "body"
    }
}
