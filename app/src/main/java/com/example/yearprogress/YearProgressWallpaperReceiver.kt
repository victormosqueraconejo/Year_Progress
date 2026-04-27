package com.example.yearprogress

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager

private const val ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED"
private const val ACTION_UI_MODE_CHANGED = "android.intent.action.UI_MODE_CHANGED"
private const val WALLPAPER_REFRESH_WORK_NAME = "year_progress_widget_wallpaper_refresh"

class YearProgressWallpaperReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val shouldRefresh = intent.action == ACTION_WALLPAPER_CHANGED ||
            intent.action == Intent.ACTION_CONFIGURATION_CHANGED ||
            intent.action == ACTION_UI_MODE_CHANGED

        if (!shouldRefresh) return

        val request = OneTimeWorkRequestBuilder<YearProgressWidgetRefreshWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            WALLPAPER_REFRESH_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}

