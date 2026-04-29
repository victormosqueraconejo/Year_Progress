package com.example.yearprogress.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight as UiFontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.color.DynamicThemeColorProviders
import androidx.glance.layout.Alignment
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
import java.time.LocalDate
import java.time.format.TextStyle as JavaTextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProgressBarVariant(
    porcentaje: Int,
    currentDay: Int,
    maxDays: Int
) {
    val safeProgress = (porcentaje / 100f).coerceIn(0f, 1f)
    val remainingDays = (maxDays - currentDay).coerceAtLeast(0)
    val today = LocalDate.now()
    val monthName = today.month.getDisplayName(JavaTextStyle.FULL, Locale.getDefault())
    val dateLabel = "${today.dayOfMonth} $monthName"

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(DynamicThemeColorProviders.primary)
            .padding(16.dp)
    ) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {
            Text(
                text = "TODAY'S PERSPECTIVE",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Box(
                modifier = GlanceModifier
                    .background(DynamicThemeColorProviders.onPrimary)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${today.year}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = DynamicThemeColorProviders.primary
                    )
                )
            }
        }

        Row(
            modifier = GlanceModifier.fillMaxWidth().padding(top = 6.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.Start
        ) {
            Text(
                text = dateLabel,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DynamicThemeColorProviders.onPrimary
                )
            )
        }

        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            Text(
                text = "Year Journey",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Text(
                text = "$currentDay / $maxDays",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary
                )
            )
        }

        ProgressBar(progress = safeProgress, modifier = GlanceModifier.padding(top = 6.dp))

        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            Text(
                text = "${porcentaje.coerceIn(0, 100)}% Complete",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary
                )
            )

            Text(
                text = "$remainingDays days remaining",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary
                ),
                modifier = GlanceModifier.defaultWeight()
            )
        }
    }
}

@Composable
fun ProgressBar(
    progress: Float,
    modifier: GlanceModifier = GlanceModifier
) {
    val safeProgress = progress.coerceIn(0f, 1f)
    val barHeight = 10.dp
    val barWidth = 200.dp
    val filledWidth = barWidth * safeProgress

    Box(
        modifier = modifier
            .width(barWidth)
            .height(barHeight)
            .background(DynamicThemeColorProviders.onPrimary)
    ) {
        Box(
            modifier = GlanceModifier
                .width(filledWidth)
                .fillMaxHeight()
                .background(DynamicThemeColorProviders.primary),
            content = {}
        )
    }
}
