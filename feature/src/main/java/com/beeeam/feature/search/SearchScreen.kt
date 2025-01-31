package com.beeeam.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beeeam.feature.component.JokeSearchItem
import com.beeeam.feature.component.SimpleJokeUiFrame
import com.beeeam.feature.extension.collectWithLifecycle
import com.beeeam.feature.extension.dateFormat
import com.beeeam.feature.theme.mainColor

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    viewModel.sideEffect.collectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is SearchSideEffect.ShowSearchResultLoaded -> viewModel.onShowToast("Joke Loaded!!")
        }
    }

    SearchScreen(
        uiState = uiState,
        onSearchFieldChanged = viewModel::updateJokeSearchValue,
        onClickSearch = viewModel::searchJoke,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchState,
    onSearchFieldChanged: (String) -> Unit,
    onClickSearch: (String) -> Unit,
) {
    SimpleJokeUiFrame(
        searchValue = uiState.jokeSearchValue,
        onSearchFieldChanged = onSearchFieldChanged,
        onClickSearch = onClickSearch
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Total: ${uiState.jokeSearchResultCount}",
                color = mainColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            LazyColumn {
                items(items = uiState.jokeSearchResult) { content ->
                    JokeSearchItem(
                        category = "category",
                        joke = content.joke,
                        date = content.updateDate.dateFormat()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}