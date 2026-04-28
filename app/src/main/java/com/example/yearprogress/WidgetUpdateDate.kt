package com.example.yearprogress

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import java.util.Calendar

@RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
fun WidgetUpdateDate(context: Context) {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


    val intent = Intent(context, DateChangeReciever::class.java).apply {
        action = "com.example.yearprogress.ACTION_MIDNIGHT_UPDATE"
    }


    val pendigIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.DAY_OF_YEAR, 1)
    }

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        pendigIntent
    )
}