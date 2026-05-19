package com.example.yearprogress

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.glance.material3.ColorProviders
import androidx.glance.GlanceTheme
import com.example.yearprogress.ui.theme.DarkColorScheme
import com.example.yearprogress.components.CircularProgressVariant
import com.example.yearprogress.components.ProgressBarVariant
import com.example.yearprogress.utils.Utils.dataStore
import com.example.yearprogress.utils.Utils.getCircularProgressMaskBitmap
import com.example.yearprogress.utils.Utils.getCircularTrackMaskBitmap
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.Year



class YearProgressWidget : GlanceAppWidget() {


    override val sizeMode: SizeMode = SizeMode.Exact

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val currentDay = LocalDate.now().dayOfYear
        val maxDays = Year.now().length()
        val progress = currentDay / maxDays.toFloat()
        val porcentaje = (progress * 100)

        val trackBitmap = getCircularTrackMaskBitmap()
        val progressBitmap = getCircularProgressMaskBitmap(progress)
        val KEY = intPreferencesKey("widget_variant")


        val preferences = context.dataStore.data.first()
        val selectVariant = preferences[KEY] ?: 1

        val darkScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicDarkColorScheme(context)
        } else {
            DarkColorScheme
        }

        val glanceColorProviders = ColorProviders(
            light = darkScheme,
            dark = darkScheme
        )

        provideContent {
            GlanceTheme(colors = glanceColorProviders) {
                if ( selectVariant == 1) {
                    ProgressBarVariant(
                        porcentaje = porcentaje,
                        maxDays = maxDays,
                        currentDay = currentDay
                    )
                }
                else {
                    CircularProgressVariant(
                        trackImage = trackBitmap,
                        progressImage = progressBitmap,
                        porcentaje = porcentaje,
                        currentDay = currentDay,
                        maxDays = maxDays
                    )
                }
            }
        }
    }
}
