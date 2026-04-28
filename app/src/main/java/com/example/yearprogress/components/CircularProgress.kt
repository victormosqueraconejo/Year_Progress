package com.example.yearprogress.components

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
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
import com.example.yearprogress.CreateCircularProgressBitmap
import com.example.yearprogress.R
import java.time.Year


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CircularProgressVariant(
    trackImage: Bitmap,
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
                .padding(bottom = 4.dp)
                .defaultWeight()
            ,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "${Year.now()}",
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
                modifier = GlanceModifier.size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(trackImage),
                    contentDescription = null,
                    modifier = GlanceModifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(DynamicThemeColorProviders.onBackground)
                )
                Image(
                    provider = ImageProvider(progressImage),
                    contentDescription = null,
                    modifier = GlanceModifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(DynamicThemeColorProviders.primary)
                )
                Text(
                    text = "$porcentaje %",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DynamicThemeColorProviders.primary
                    )
                )
            }

            Text(
                text = "$currentDay/$maxDays",
                modifier = GlanceModifier.fillMaxWidth().defaultWeight().padding(18.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = DynamicThemeColorProviders.primary
                )
            )
        }
    }
}

