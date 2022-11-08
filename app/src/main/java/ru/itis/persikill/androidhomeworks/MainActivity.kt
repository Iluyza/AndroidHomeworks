package ru.itis.persikill.androidhomeworks

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        with(binding) {
            btnSelectTime.setOnClickListener {
                showTimePicker()
            }
            btnSetAlarm.setOnClickListener {
                setAlarm()
            }
            btnCancelAlarm.setOnClickListener {
                cancelAlarm()
            }
        }

    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(baseContext, 0, intent, 0)
        }
        alarmManager.cancel(intent)

        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_LONG).show()
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(baseContext, 0, intent, 0)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, intent
        )
        Toast.makeText(this, "ALARM WAS SET!", Toast.LENGTH_LONG).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("SELECT ALARM TIME")
            .build()
        picker.show(supportFragmentManager, "TIMEPICKER")

        picker.addOnPositiveButtonClickListener {
            binding.tvTime.text = picker.hour.toString() + " : " + picker.minute.toString()
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }


    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "AlarmChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("wakeup", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(channel)
        }
    }
}