package com.example.yearprogress

import androidx.glance.unit.ColorProvider


data class WidgetPaletteClass(
    val background: ColorProvider,
    val onBackground: ColorProvider,
    val primary: ColorProvider,
    val onPrimary: ColorProvider,
    val surfaceVariant: ColorProvider,
    val primaryInt: Int? = null,
    val onPrimaryInt: Int?  = null
)
