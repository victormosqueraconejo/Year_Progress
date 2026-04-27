package com.example.yearprogress

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.yearprogress.ui.theme.YearProgressTheme

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
            modifier = GlanceModifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "2026",
                modifier = GlanceModifier.defaultWeight().padding(8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                   fontWeight = FontWeight.Bold
                )
            )

            Image(
                provider = ImageProvider(R.drawable.ic_finance_mode),
                contentDescription = null,
                modifier = GlanceModifier.size(24.dp)
            )





        }

    }
}




@Preview()
@Composable
fun WidgetPreview() {

    YearProgressContent()

}