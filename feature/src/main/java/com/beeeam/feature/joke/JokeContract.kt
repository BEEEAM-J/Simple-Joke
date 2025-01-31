package com.beeeam.feature.joke

import com.beeeam.domain.model.Category
import com.beeeam.feature.base.SideEffect
import com.beeeam.feature.base.UiState

data class JokeState(
    val category: Category = Category(),
    val joke: String = "Simple Joke!",
    val jokeSearchValue: String = "",
    val toastMsg: String = "",
    val toastVisible: Boolean = false,
    val isDropDownExpanded: Boolean = false,
): UiState

sealed interface JokeSideEffect: SideEffect {
    object ShowJokeLoaded: JokeSideEffect
    object NavigateToSearch: JokeSideEffect
}