package com.example.yearprogress

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.DynamicThemeColorProviders
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import kotlin.math.roundToInt

class YearProgressWidget : GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val currentDay = 117 // todo: Hacer dinamico
        val maxDays = 365
        val progress = currentDay / maxDays.toFloat()
        val porcentaje = (progress * 100).roundToInt()

        val bitmap = getCircularBitmap(
            context = context,
            progress = progress
        )

        provideContent {
            YearProgressContent(
                progressImage = bitmap,
                porcentaje = porcentaje,
                currentDay = currentDay,
                maxDays = maxDays
            )
        }
    }
}

@Composable
fun YearProgressContent(
    progressImage: Bitmap,
    porcentaje: Int,
    currentDay: Int,
    maxDays: Int
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .cornerRadius(8.dp)
            .background(DynamicThemeColorProviders.background)
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(8.dp)
                .defaultWeight(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "2026",
                modifier = GlanceModifier.defaultWeight().padding(8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DynamicThemeColorProviders.primary
                )
            )

            Image(
                provider = ImageProvider(R.drawable.ic_finance_mode),
                contentDescription = null,
                modifier = GlanceModifier
                    .padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
                    .size(36.dp),
                colorFilter = ColorFilter.tint(DynamicThemeColorProviders.primary)
            )
        }

        Column(
            modifier = GlanceModifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            Box(
                modifier = GlanceModifier.size(75.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(progressImage),
                    contentDescription = null,
                    modifier = GlanceModifier.fillMaxSize()
                )
                Text(
                    text = "$porcentaje %",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DynamicThemeColorProviders.primary
                    )
                )
            }

            Text(
                text = "$currentDay/$maxDays",
                modifier = GlanceModifier.fillMaxWidth().defaultWeight().padding(18.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = DynamicThemeColorProviders.primary
                )
            )
        }
    }
}

private fun getCircularBitmap(context: Context, progress: Float): Bitmap {
    val trackColor = DynamicThemeColorProviders.background.getColor(context).toArgb()
    val progressColor = DynamicThemeColorProviders.primary.getColor(context).toArgb()

    return CreateCircularProgressBitmap(
        sizePx = 220,
        progress = progress,
        trackColor = trackColor,
        progressColor = progressColor,
        strokePx = 18f
    )
}
