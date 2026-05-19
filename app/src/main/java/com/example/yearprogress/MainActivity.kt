package com.example.yearprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.collectAsState
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yearprogress.repository.WidgetSelecionRepository
import com.example.yearprogress.ui.theme.YearProgressTheme
import com.example.yearprogress.viewmodel.WidgetSelectionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Year
import com.example.yearprogress.components.NativeProgressBar
import com.example.yearprogress.components.NativeCircularProgress


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YearProgressTheme {
                val viewModel: WidgetSelectionViewModel = viewModel(
                    factory = WidgetSelectionViewModel.Factory(
                        WidgetSelecionRepository(applicationContext)
                    )
                )
                val coroutineScope = rememberCoroutineScope()

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)) { innerPadding ->

                    Row(
                        modifier = Modifier.padding(innerPadding.calculateTopPadding())
                    ) {

                        val currentDay = LocalDate.now().dayOfYear
                        val maxDays = Year.now().length()
                        val progress = currentDay / maxDays.toFloat()
                        
                        val selectedVariant by viewModel.selectionVariant.collectAsState(initial = 1)

                        CardSelectVariant(
                            previewBlock = { NativeProgressBar(progress, currentDay, maxDays) },
                            text = "Barra (Variante 1)",
                            isSelected = selectedVariant == 1,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.selectVariant(1)
                                    YearProgressWidget().updateAll(applicationContext)
                                }
                            }
                        )

                        CardSelectVariant(
                            previewBlock = { NativeCircularProgress(progress, currentDay, maxDays) },
                            text = "Círculo (Variante 2)",
                            isSelected = selectedVariant == 2,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.selectVariant(2)
                                    YearProgressWidget().updateAll(applicationContext)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun CardSelectVariant(
    previewBlock: @Composable () -> Unit,
    text : String,
    isSelected: Boolean,
    modifier: Modifier,
    onClick : () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        onClick = {
            if (!isSelected) {
                onClick()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                previewBlock()
            }

            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}