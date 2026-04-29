package com.example.yearprogress

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DateChangeReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        val pendingResult = goAsync()

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val manager = GlanceAppWidgetManager(context)
                val widget = YearProgressWidget()

                val ids = manager.getGlanceIds(YearProgressWidget::class.java)

                ids.forEach { id ->
                    widget.update(context, id)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                pendingResult.finish()
            }
        }
    }
}