package com.beeeam.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beeeam.feature.component.CategoryDropDown
import com.beeeam.feature.theme.black
import com.beeeam.feature.theme.mainColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.beeeam.feature.theme.SimpleJokeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainRoute()
                }
            }
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadCategory()
    }

    MainScreen(
        onClickCategoryItem = viewModel::loadJoke
    )
}

@Composable
fun MainScreen(
    onClickCategoryItem: (String) -> Unit
) {
    var title by remember { mutableStateOf("Simple Joke!") }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            modifier = Modifier.padding(42.dp),
            painter = painterResource(id = R.drawable.homer_simpson),
            contentDescription = ""
        )
        Text(
            text = title,
            color = mainColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
        )
        CategoryDropDown(
            dropDownState = isDropDownMenuExpanded,
            dropDownClick = {
                isDropDownMenuExpanded = !isDropDownMenuExpanded
            },
            dropDownItemClick = onClickCategoryItem,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    com.beeeam.feature.theme.SimpleJokeTheme {
        MainScreen(
            onClickCategoryItem = {}
        )
    }
}