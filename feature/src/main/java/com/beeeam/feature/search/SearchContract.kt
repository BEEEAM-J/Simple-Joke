package com.beeeam.feature.search

import com.beeeam.domain.model.JokeInfo
import com.beeeam.domain.model.SearchResult
import com.beeeam.feature.base.SideEffect
import com.beeeam.feature.base.UiState

data class SearchState(
    val jokeSearchValue: String = "",
    val jokeSearchResultCount: Int = 0,
    val jokeSearchResult: List<JokeInfo> = listOf(),
    val toastMsg: String = "",
    val toastVisible: Boolean = false,
): UiState

sealed interface SearchSideEffect: SideEffect {
    object ShowSearchResultLoaded: SearchSideEffect
}