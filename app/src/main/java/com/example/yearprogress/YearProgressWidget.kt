package com.example.yearprogress

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
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

        val bitmap = CreateCircularProgressBitmap(
            sizePx = 220,
            progress = progress,
            trackColor = android.graphics.Color.parseColor("#33FFFFFF"),
            android.graphics.Color.parseColor("#FFFFFFFF"),
            strokePx  = 18f
        )


        val palette = GetWidgetPalette(context)

        provideContent {

            YearProgressContent(
                bitmap,
                "$porcentaje",
                palette
            )

        }
    }




}


@Composable
fun YearProgressContent(
    progressImage: Bitmap,
    stringPorncenatje : String,
    paletteClass: WidgetPaletteClass
) {
    Column(
        modifier = GlanceModifier.fillMaxSize()

            .cornerRadius(8.dp)
            .background(paletteClass.background)

    ) {

        Row(
            modifier = GlanceModifier.fillMaxWidth().padding(8.dp).defaultWeight(),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "2026",
                modifier = GlanceModifier.defaultWeight().padding(8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                   fontWeight = FontWeight.Bold,
                    color = paletteClass.primary
                )
            )

            Image(
                provider = ImageProvider(R.drawable.ic_finance_mode),
                contentDescription = null,
                modifier = GlanceModifier
                    .padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
                    .size(36.dp)
            )





        }

        Column(
            modifier = GlanceModifier.fillMaxWidth().padding(12.dp),
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {

            Box (
                modifier = GlanceModifier.size(75.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(progressImage),
                    contentDescription = null,
                    modifier = GlanceModifier.fillMaxSize()
                )
                Text(
                    text = "$stringPorncenatje %",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

            Text(
                text = "117/365",
                modifier = GlanceModifier.fillMaxWidth().defaultWeight().padding(18.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )


        }


    }



}

