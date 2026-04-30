package com.example.yearprogress

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import com.example.yearprogress.repository.WidgetSelecionRepository
import com.example.yearprogress.ui.theme.YearProgressTheme
import com.example.yearprogress.viewmodel.WidgetSelectionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YearProgressTheme {





                var selectedId by remember { mutableStateOf(1) }


                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)) { innerPadding ->

                  Row(
                        modifier = Modifier.padding(innerPadding.calculateTopPadding())
                  ) {


                      CardSelectVariant(
                          R.drawable.variante_1,
                          "Variante 1",
                          Modifier.weight(1f),
                          enable = true,
                          onClick = { if (selectedId != 1) selectedId = 1

                                    },
                          this@MainActivity,
                          variant = 1
                      )

                      CardSelectVariant(
                          R.drawable.desing,
                          "Variante 2",
                          Modifier.weight(1f),
                          enable = true,
                          onClick = {
                              if (selectedId != 2) {
                                  selectedId = 2

                              }
                          },
                          this@MainActivity,
                          variant = 2
                      )



                  }


                }
            }
        }
    }
}



@Composable
fun CardSelectVariant(
    image : Int,
    text : String,
    modifier: Modifier,
    enable : Boolean,
    onClick : () -> Unit,
    context: Context,
    variant : Int
) {

    val repo = WidgetSelecionRepository(context)
    val viewModelWidgetVariant = WidgetSelectionViewModel(repo)

    ElevatedCard(
        enabled = enable,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),

        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .heightIn(min = 150.dp, max = 200.dp),
        onClick = {
            onClick()
            Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()


            CoroutineScope(Dispatchers.Default).launch {
                viewModelWidgetVariant.selectVariant(variant)
                YearProgressWidget().updateAll(context)

            }


        }

    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Inside
            )

                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )



        }


    }


}