package com.example.yearprogress

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.glance.color.ColorProvider

fun GetWidgetPalette(context: Context): WidgetPaletteClass {
    val isNight =
        (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val background = ContextCompat.getColor(context, android.R.color.system_neutral1_900)
        val onBackground = ContextCompat.getColor(context, android.R.color.system_neutral1_50)
        val primary = ContextCompat.getColor(context, android.R.color.system_accent1_400)
        val onPrimary = ContextCompat.getColor(context, android.R.color.system_neutral1_900)

        WidgetPaletteClass(
            background = ColorProvider(day = Color(background), night = Color(background)),
            onBackground = ColorProvider(day = Color(onBackground), night = Color(onBackground)),
            primary = ColorProvider(day = Color(primary), night = Color(primary)),
            onPrimary = ColorProvider(day = Color(onPrimary), night = Color(onPrimary)),
            surfaceVariant = ColorProvider(day = Color(background), night = Color(background)),
            primaryInt = primary,
            onPrimaryInt = onPrimary
        )
    } else {
        val background = if (isNight) 0xFF121212.toInt() else 0xFFF5F5F5.toInt()
        val onBackground = if (isNight) 0xFFFFFFFF.toInt() else 0xFF111111.toInt()
        val primary = if (isNight) 0xFF008DFF.toInt() else 0xFFE91E63.toInt()
        val onPrimary = if (isNight) 0xFF001CEE.toInt() else 0xFFFFFFFF.toInt()
        val surfaceVariant = if (isNight) 0xFF2A2D2A.toInt() else 0xFFE0E3DD.toInt()

        WidgetPaletteClass(
            background = ColorProvider(day = Color(background), night = Color(background)),
            onBackground = ColorProvider(day = Color(onBackground), night = Color(onBackground)),
            primary = ColorProvider(day = Color(primary), night = Color(primary)),
            onPrimary = ColorProvider(day = Color(onPrimary), night = Color(onPrimary)),
            surfaceVariant = ColorProvider(day = Color(surfaceVariant), night = Color(surfaceVariant)),
            primaryInt = primary,
            onPrimaryInt = onPrimary
        )
    }
}