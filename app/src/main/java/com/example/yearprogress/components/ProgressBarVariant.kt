package com.example.yearprogress.components


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.color.DynamicThemeColorProviders
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle


@Composable
fun  ProgressBarVariant (
    porcentaje: Int,
    currentDay: Int,
    maxDays: Int

) {

    Column(
        modifier = GlanceModifier.fillMaxSize().background(DynamicThemeColorProviders.background)
    ) {

        Text(
            text = "Progreso del Año",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DynamicThemeColorProviders.primary,
            ),
            modifier = GlanceModifier.fillMaxWidth().defaultWeight()
        )


        ProgressBar(porcentaje.toFloat())






    }

}

@Composable
fun ProgressBar(progress: Float) {
    val safeProgress = progress.coerceIn(0f, 1f)
    val size = LocalSize.current
    val barHeight = 10.dp
    val horizontalPadding = 10.dp
    val totalWidth = size.width - (horizontalPadding * 2)
    val filledWidth = totalWidth * safeProgress

    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(horizontalPadding)
            .height(barHeight)
            .background(DynamicThemeColorProviders.onBackground)
    ) {
        Box(
            modifier = GlanceModifier
                .width(filledWidth)
                .fillMaxHeight()
                .background(DynamicThemeColorProviders.primary)
        ) {

        }
    }
}

