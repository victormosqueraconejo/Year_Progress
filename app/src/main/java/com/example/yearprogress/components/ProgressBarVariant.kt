package com.example.yearprogress.components


import android.os.Build
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight as UiFontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.color.DynamicThemeColorProviders
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.yearprogress.utils.Utils.formatPorcentaje
import java.time.LocalDate
import java.time.format.TextStyle as JavaTextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProgressBarVariant(
    porcentaje: Float,
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
            .padding(8.dp)
            .cornerRadius(16.dp)
    ) {
        Spacer(modifier = GlanceModifier.defaultWeight())
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {
            Text(
                text = "TODAY'S PERSPECTIVE",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = DynamicThemeColorProviders.onPrimary
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Box(
                modifier = GlanceModifier
                    .background(DynamicThemeColorProviders.onPrimary).cornerRadius(8.dp)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${today.year}",
                    modifier = GlanceModifier.cornerRadius(16.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = DynamicThemeColorProviders.primary
                    )
                )
            }
        }

        Spacer(modifier = GlanceModifier.defaultWeight())


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

//        Spacer(modifier = GlanceModifier.defaultWeight())

        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 6.dp),
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
                    fontWeight = FontWeight.Bold,
                    color = DynamicThemeColorProviders.onPrimary
                )
            )
        }

        ProgressBar(progress = safeProgress, modifier = GlanceModifier)



        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            Text(
                text = "${formatPorcentaje(
                    porcentaje
                )} %" ,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DynamicThemeColorProviders.onPrimary
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Text(
                text = "$remainingDays days remaining",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DynamicThemeColorProviders.onPrimary,
                    textAlign = androidx.glance.text.TextAlign.End
                ),
                modifier = GlanceModifier.defaultWeight()
            )
        }

        Spacer(modifier = GlanceModifier.defaultWeight())
    }



}
@Composable
fun ProgressBar(
    progress: Float,
    modifier: GlanceModifier = GlanceModifier
) {
    val safeProgress = progress.coerceIn(0f, 1f)


    val totalWidth = LocalSize.current.width
    val filledWidth = totalWidth * safeProgress

    val barHeight = 16.dp
    val roundedCorner = barHeight / 2

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .height(barHeight)
            .cornerRadius(roundedCorner)
            .background(DynamicThemeColorProviders.onPrimary)
    ) {
        if (safeProgress > 0f) {
            Box(
                modifier = GlanceModifier
                    .width(filledWidth)
                    .fillMaxHeight()
                    .cornerRadius(roundedCorner)
                    .background(DynamicThemeColorProviders.onBackground),
                content = {},

            )
        }
    }
}