package com.example.yearprogress

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.glance.color.DynamicThemeColorProviders
import androidx.glance.unit.ColorProvider

fun GetWidgetPalette(context: Context): WidgetPaletteClass {
    val isNight =
        (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        WidgetPaletteClass(
            background = DynamicThemeColorProviders.background,
            onBackground = DynamicThemeColorProviders.onBackground,
            primary = DynamicThemeColorProviders.primary,
            onPrimary = DynamicThemeColorProviders.onPrimary,
            surfaceVariant = DynamicThemeColorProviders.surfaceVariant,
            primaryInt = DynamicThemeColorProviders.primary.getColor(context).toArgb(),
            onPrimaryInt = DynamicThemeColorProviders.onPrimary.getColor(context).toArgb()
        )
    } else {
        val background = if (isNight) 0xFF121212.toInt() else 0xFFF5F5F5.toInt()
        val onBackground = if (isNight) 0xFFFFFFFF.toInt() else 0xFF111111.toInt()
        val primary = if (isNight) 0xFF008DFF.toInt() else 0xFFE91E63.toInt()
        val onPrimary = if (isNight) 0xFF001CEE.toInt() else 0xFFFFFFFF.toInt()
        val surfaceVariant = if (isNight) 0xFF2A2D2A.toInt() else 0xFFE0E3DD.toInt()

        WidgetPaletteClass(
            background = ColorProvider(Color(background)),
            onBackground = ColorProvider(Color(onBackground)),
            primary = ColorProvider(Color(primary)),
            onPrimary = ColorProvider(Color(onPrimary)),
            surfaceVariant = ColorProvider(Color(surfaceVariant)),
            primaryInt = primary,
            onPrimaryInt = onPrimary
        )
    }
}
