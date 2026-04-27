package com.example.yearprogress

import android.content.Context

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

class YearProgressWidget : GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {

            YearProgressContent()

        }
    }




}


@Composable
fun YearProgressContent() {
    Column(
        modifier = GlanceModifier.fillMaxSize()

            .cornerRadius(8.dp)
            .background(MaterialTheme.colorScheme.background)

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


        // TODO: Progress Circle
        Row(
            modifier = GlanceModifier.fillMaxWidth().defaultWeight(),
            verticalAlignment = Alignment.Bottom
        ) {
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

