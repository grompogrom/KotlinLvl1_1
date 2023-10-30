package com.pogrom.kotlinlvl1_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pogrom.kotlinlvl1_1.ui.theme.KotlinLvl1_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLvl1_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(),
) {
    val items = mainViewModel.items
    val context = LocalContext.current
    val columnsCount = context.resources.getInteger(R.integer.columns_count)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NumbersGrid(
            numbers = items,
            onAddItemClick = { mainViewModel.addItem() },
            modifier = Modifier.height(200.dp),
            columnsCount = columnsCount
        )
        
    }
}


@Composable
fun NumbersGrid(
    numbers: List<Item>,
    columnsCount: Int,
    onAddItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(columns = GridCells.Fixed(columnsCount)) {
        items(numbers) { number ->
            GridItem(
                text = number.index.toString(),
                color = if (number.color) Color.Red else Color.Blue,
                modifier = Modifier.padding(10.dp)
            )
        }
        item(span = { GridItemSpan(columnsCount) }) {
            Button(
                onClick = onAddItemClick,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = LocalContext.current.getString(R.string.btn_add_item))
            }
        }

    }
}

@Composable
fun GridItem(
    text: String,
    color: Color ,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(20.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
            )
    }
}

@Preview
@Composable
private fun GridItemPrev() {
    KotlinLvl1_1Theme {
        Surface(
            color = Color.DarkGray
        ) {
            GridItem(
                text = "2",
                color = Color.Blue
            )
        }
    }
}

@Preview()
@Composable
private fun NumberGridPrev() {
    KotlinLvl1_1Theme {
        Surface {
            NumbersGrid(
                numbers = listOf(1,2,3,4).map { Item(it, it % 2 == 0) },
                3,
                {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPrev() {
    KotlinLvl1_1Theme {
        MainScreen()
    }
}

