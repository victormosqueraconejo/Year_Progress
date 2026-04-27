package com.example.yearprogress

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED"
private const val ACTION_UI_MODE_CHANGED = "android.intent.action.UI_MODE_CHANGED"

class YearProgressWallpaperReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val shouldRefresh = intent.action == ACTION_WALLPAPER_CHANGED ||
            intent.action == Intent.ACTION_CONFIGURATION_CHANGED ||
            intent.action == ACTION_UI_MODE_CHANGED

        if (!shouldRefresh) return

        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                YearProgressWidget().updateAll(context)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
