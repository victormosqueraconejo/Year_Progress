package com.example.yearprogress.components
import androidx.glance.GlanceTheme

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background

import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.example.yearprogress.WidgetUpdateAction
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.action.clickable
import com.example.yearprogress.CreateCircularProgressBitmap
import com.example.yearprogress.R
import com.example.yearprogress.utils.Utils
import java.time.LocalDate
import java.time.Year


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CircularProgressVariant(
    trackImage: Bitmap,
    progressImage: Bitmap,
    porcentaje: Float,
    currentDay: Int,
    maxDays: Int
) {
    val size = LocalSize.current
    val isCompact = size.height < 120.dp
    val today = LocalDate.now()
    val dateLabel = "${today.dayOfMonth} ${today.month}"


    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .cornerRadius(8.dp)
            .background(GlanceTheme.colors.primary)
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "${Year.now()}",
                modifier = GlanceModifier.defaultWeight().padding(8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = GlanceTheme.colors.onPrimary
                )
            )

            Image(
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = "Refresh",
                modifier = GlanceModifier
                    .padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
                    .size(24.dp)
                    .clickable(onClick = actionRunCallback<WidgetUpdateAction>()),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimary)
            )

        }


            if (!isCompact) {

                Column(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
                    verticalAlignment = Alignment.Vertical.CenterVertically
                ) {
                    Box(
                        modifier = GlanceModifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            provider = ImageProvider(trackImage),
                            contentDescription = null,
                            modifier = GlanceModifier.fillMaxSize(),
                            colorFilter = ColorFilter.tint(GlanceTheme.colors.onBackground)
                        )
                        Image(
                            provider = ImageProvider(progressImage),
                            contentDescription = null,
                            modifier = GlanceModifier.fillMaxSize(),
                            colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimary)
                        )
                        Text(
                            text = "${Utils.formatPorcentaje(porcentaje)}%",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = GlanceTheme.colors.onPrimary
                            )
                        )
                    }

            }


        }
        Text(
            text = "$currentDay/$maxDays",
            modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GlanceTheme.colors.onPrimary
            )
        )



        Text(
            text = dateLabel,
            modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = GlanceTheme.colors.onPrimary
            )
        )
    }
}
