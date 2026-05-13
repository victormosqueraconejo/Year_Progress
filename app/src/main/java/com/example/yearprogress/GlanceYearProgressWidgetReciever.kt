package com.example.yearprogress

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class GlanceYearProgressWidgetReciever : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = YearProgressWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        WidgetUpdateScheduler.scheduleMidnightUpdate(context)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        WidgetUpdateScheduler.scheduleMidnightUpdate(context)
    }
}