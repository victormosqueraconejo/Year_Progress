package com.example.yearprogress

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
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
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.example.yearprogress.components.CircularProgressVariant
import com.example.yearprogress.components.ProgressBarVariant
import com.example.yearprogress.utils.Utils.getCircularProgressMaskBitmap
import com.example.yearprogress.utils.Utils.getCircularTrackMaskBitmap
import java.time.LocalDate
import java.time.Year
import kotlin.math.roundToInt

class YearProgressWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Exact

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val currentDay = LocalDate.now().dayOfYear
        val maxDays = Year.now().length()
        val progress = currentDay / maxDays.toFloat()
        val porcentaje = (progress * 100)

        val trackBitmap = getCircularTrackMaskBitmap()
        val progressBitmap = getCircularProgressMaskBitmap(progress)

        provideContent {

//            ProgressBarVariant(
//                porcentaje = porcentaje,
//                maxDays = maxDays,
//                currentDay = currentDay
//
//            )


            CircularProgressVariant(
                trackImage = trackBitmap,
                progressImage = progressBitmap,
                porcentaje = porcentaje,
                currentDay = currentDay,
                maxDays = maxDays
            )
        }
    }
}
