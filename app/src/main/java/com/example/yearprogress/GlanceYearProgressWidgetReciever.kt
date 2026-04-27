package com.example.yearprogress

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

private const val WIDGET_REFRESH_WORK_NAME = "year_progress_widget_refresh"

class GlanceYearProgressWidgetReciever : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = YearProgressWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        schedulePeriodicRefresh(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        schedulePeriodicRefresh(context)
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        WorkManager.getInstance(context).cancelUniqueWork(WIDGET_REFRESH_WORK_NAME)
    }


    private fun schedulePeriodicRefresh(context: Context) {
        val request = PeriodicWorkRequestBuilder<YearProgressWidgetRefreshWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WIDGET_REFRESH_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }
}