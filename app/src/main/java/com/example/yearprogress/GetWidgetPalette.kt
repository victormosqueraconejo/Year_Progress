package com.example.yearprogress

import android.content.Context
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.glance.color.ColorProvider

import com.google.android.material.color.MaterialColors

fun GetWidgetPalette(context: Context): WidgetPaletteClass {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val background = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.backgroundColor,
            "#121212".toColorInt()
        )
        val onBackground = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnBackground,
            "#FFFFFF".toColorInt()
        )
        val primary = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorPrimaryFixed,
            "#80CBC4".toColorInt()
        )
        val onPrimary = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnPrimary,
            "#003733".toColorInt()
        )
        val surfaceVariant = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceVariant,
            "#2A2D2A".toColorInt()
        )

        WidgetPaletteClass(
            background = ColorProvider(
                day = Color(background),
                night = Color(background)
            ),
            onBackground = ColorProvider(
                day = Color(onBackground),
                night = Color(onBackground)
            ),
            primary = ColorProvider(
                day = Color(primary),
                night = Color(primary)
            ),
            onPrimary = ColorProvider(
                day = Color(onPrimary),
                night = Color(onPrimary)
            ),
            surfaceVariant = ColorProvider(
                day = Color(surfaceVariant),
                night = Color(surfaceVariant)
            )
        )
    } else {
        WidgetPaletteClass(
            background = ColorProvider(
                day = Color(0xFFF5F5F5),
                night = Color(0xFF121212)
            ),
            onBackground = ColorProvider(
                day = Color(0xFF111111),
                night = Color(0xFFFFFFFF)
            ),
            primary = ColorProvider(
                day = Color(0xFF006C4C),
                night = Color(0xFF7DDBB8)
            ),
            onPrimary = ColorProvider(
                day = Color(0xFFFFFFFF),
                night = Color(0xFF003829)
            ),
            surfaceVariant = ColorProvider(
                day = Color(0xFFE0E3DD),
                night = Color(0xFF2A2D2A)
            )
        )
    }
}