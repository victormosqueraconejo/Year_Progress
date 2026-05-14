package com.example.yearprogress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yearprogress.R
import com.example.yearprogress.utils.Utils
import java.time.LocalDate
import java.time.Year
import java.util.Locale

@Composable
fun NativeProgressBar(progress: Float, currentDay: Int, maxDays: Int, modifier: Modifier = Modifier) {
    val today = LocalDate.now()
    val monthName = today.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault())
    val dateLabel = "${today.dayOfMonth} $monthName"
    val remainingDays = (maxDays - currentDay).coerceAtLeast(0)
    val porcentaje = progress * 100

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("TODAY'S PERSPECTIVE", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.weight(1f))
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(8.dp)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                Text("${today.year}", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(dateLabel, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Journey", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.weight(1f))
            Text("$currentDay/$maxDays", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 10.sp), color = MaterialTheme.colorScheme.onPrimary)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth().height(10.dp).background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(5.dp)), contentAlignment = Alignment.CenterStart) {
            if (progress > 0) {
                Box(modifier = Modifier.fillMaxWidth(progress.coerceIn(0f, 1f)).fillMaxHeight().background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(5.dp)))
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${Utils.formatPorcentaje(porcentaje)}%", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.weight(1f))
            Text("$remainingDays remaining", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun NativeCircularProgress(progress: Float, currentDay: Int, maxDays: Int, modifier: Modifier = Modifier) {
    val porcentaje = progress * 100
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Text("${Year.now()}", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.weight(1f))
            Icon(painter = painterResource(R.drawable.ic_finance_mode), contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(70.dp)) {
            CircularProgressIndicator(
                progress = 1f,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                strokeWidth = 6.dp,
                modifier = Modifier.fillMaxSize()
            )
            CircularProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 6.dp,
                modifier = Modifier.fillMaxSize(),
                strokeCap = StrokeCap.Round
            )
            Text("${Utils.formatPorcentaje(porcentaje)}%", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("$currentDay/$maxDays", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary)
    }
}
