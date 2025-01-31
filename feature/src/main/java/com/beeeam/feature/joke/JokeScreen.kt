package com.beeeam.feature.joke

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.beeeam.feature.R
import com.beeeam.feature.component.CategoryDropDown
import com.beeeam.feature.component.SimpleJokeToast
import com.beeeam.feature.component.SimpleJokeUiFrame
import com.beeeam.feature.extension.collectWithLifecycle
import com.beeeam.feature.theme.SimpleJokeTheme
import com.beeeam.feature.theme.mainColor

@Composable
fun JokeRoute(
    navController: NavController,
    viewModel: JokeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    viewModel.sideEffect.collectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is JokeSideEffect.ShowJokeLoaded -> viewModel.onShowToast("Joke Loaded!!")
            is JokeSideEffect.NavigateToSearch -> {
                Log.d("Beeeam", "Navigate To Search")
                navController.navigate("search")
            }
        }
    }

    LaunchedEffect(key1 = Unit) { viewModel.getCategory() }

    JokeScreen(
        uiState = uiState,
        onClickSearchBar = viewModel::navigateToSearch,
        onClickDropDown = viewModel::showCategoryDropDown,
        onDismissDropDown = viewModel::hideCategoryDropDown,
        onClickCategoryItem = viewModel::getJoke,
    )
}

@Composable
fun JokeScreen(
    uiState: JokeState = JokeState(),
    onClickSearchBar: () -> Unit,
    onClickDropDown: () -> Unit,
    onDismissDropDown: () -> Unit,
    onClickCategoryItem: (String) -> Unit,
    ) {
    SimpleJokeUiFrame(
        searchValue = uiState.jokeSearchValue,
        onClickSearchBar = onClickSearchBar,
        onSearchFieldChanged = {},
        onClickSearch = {},
    ) {
        Image(
            modifier = Modifier.padding(42.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_homer_simpson),
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
        Box(modifier = Modifier.padding(32.dp)) {
            CategoryDropDown(
                dropDownList = uiState.category,
                dropDownState = uiState.isDropDownExpanded,
                dropDownClick = onClickDropDown,
                dropDownDismiss = onDismissDropDown,
                dropDownItemClick = onClickCategoryItem,
            )
        }
    }
    SimpleJokeToast(
        msg = uiState.toastMsg,
        visible = uiState.toastVisible,
    )
}

@Preview(showSystemUi = true)
@Composable
fun JokeScreenPreview() {
    SimpleJokeTheme {
        JokeScreen(
            onClickSearchBar = {},
            onClickDropDown = {},
            onDismissDropDown = {},
            onClickCategoryItem = {},
        )
    }
}