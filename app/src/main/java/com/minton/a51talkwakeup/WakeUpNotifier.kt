package com.minton.a51talkwakeup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.app.Notification
import android.app.TaskStackBuilder
import android.content.Context
import android.support.v4.app.NotificationCompat


class WakeUpNotifier : AppCompatActivity() {

    val mNotificationId = 420

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up_notifier)

        val CHANNEL_ID = "default"
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("51TalkWakeUp")
                .setContentText("Know whether you need to wake up!")
        // create an explicit intent for an activity in my app
        val resultIntent = Intent(this, WakeUpNotifier::class.java)

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your app to the Home screen.
        val stackBuilder = TaskStackBuilder.create(this)
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(WakeUpNotifier::class.java)
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)

        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // mNotificationId is a unique integer your app uses to identify the
    // notification. For example, to cancel the notification, you can pass its ID
    // number to NotificationManager.cancel().
        mNotificationManager.notify(mNotificationId, mBuilder.build())
    }

}
