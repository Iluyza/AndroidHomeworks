package ru.itis.persikill.androidhomeworks


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, Intent(context, AlarmActivity::class.java), 0
        )

        val builder = NotificationCompat.Builder(context!!, "wakeup")
            .setSmallIcon(R.drawable.ic_baseline_warning_24)
            .setContentTitle("ПРОСЫПАЙСЯ! СКОРО ДЕДЛАЙН!!!")
            .setContentText("Главное встань - проснешься потом!")
            .setColor(ContextCompat.getColor(context, R.color.purple_200))
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 1000))
            .setSound(Uri.parse("android.resources://" + context.packageName + "/" + R.raw.song_alarm))
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)


        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())

    }
}