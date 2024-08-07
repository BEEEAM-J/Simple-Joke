package com.beeeam.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beeeam.feature.component.CategoryDropDown
import com.beeeam.feature.component.JokeSearchBar
import com.beeeam.feature.component.SimpleJokeToast
import com.beeeam.feature.extension.collectWithLifecycle
import com.beeeam.feature.theme.SimpleJokeTheme
import com.beeeam.feature.theme.black
import com.beeeam.feature.theme.mainColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleJokeTheme {
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
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    viewModel.sideEffect.collectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is MainSideEffect.ShowJokeLoaded -> viewModel.onShowToast("Joke Loaded!!")
        }
    }

    LaunchedEffect(key1 = Unit) { viewModel.getCategory() }

    MainScreen(
        uiState = uiState,
        onClickDropDown = viewModel::showCategoryDropDown,
        onDismissDropDown = viewModel::hideCategoryDropDown,
        onClickCategoryItem = viewModel::getJoke,
        onSearchFieldChanged = viewModel::updateJokeSearchValue,
    )
}

@Composable
fun MainScreen(
    uiState: MainState = MainState(),
    onClickDropDown: () -> Unit,
    onDismissDropDown: () -> Unit,
    onClickCategoryItem: (String) -> Unit,
    onSearchFieldChanged: (String) -> Unit,

) {
    Column(
        modifier = Modifier
            .background(black)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        JokeSearchBar(
            value = uiState.jokeSearchValue,
            onValueChanged = onSearchFieldChanged,
            onEnterClicked = {}
        )
        Image(
            modifier = Modifier.padding(42.dp),
            painter = painterResource(id = R.drawable.homer_simpson),
            contentDescription = ""
        )
        Text(
            text = uiState.joke,
            color = mainColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )
        CategoryDropDown(
            dropDownList = uiState.category,
            dropDownState = uiState.isDropDownExpanded,
            dropDownClick = onClickDropDown,
            dropDownDismiss = onDismissDropDown,
            dropDownItemClick = onClickCategoryItem,
        )
    }

    SimpleJokeToast(
        msg = uiState.toastMsg,
        visible = uiState.toastVisible,
    )
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    SimpleJokeTheme {
        MainScreen(
            onClickDropDown = {},
            onDismissDropDown = {},
            onClickCategoryItem = {},
            onSearchFieldChanged = {},
        )
    }
}