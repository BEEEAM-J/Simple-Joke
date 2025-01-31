package com.beeeam.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beeeam.feature.theme.black

@Composable
fun SimpleJokeUiFrame(
    searchValue: String,
    onClickSearchBar: () -> Unit = {},
    onSearchFieldChanged: (String) -> Unit,
    onClickSearch: (String) -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .background(black)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JokeSearchBar(
            modifier = Modifier.padding(top = 30.dp),
            value = searchValue,
            onClickSearchBar = onClickSearchBar,
            onValueChanged = onSearchFieldChanged,
            onEnterClicked = onClickSearch
        )
        content()
    }
}