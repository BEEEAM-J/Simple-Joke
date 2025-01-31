package com.beeeam.feature.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.beeeam.domain.model.SearchResult
import com.beeeam.domain.usecase.SearchUseCase
import com.beeeam.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): BaseViewModel<SearchState, SearchSideEffect>(
    SearchState()
) {
    fun searchJoke(searchValue: String?) = viewModelScope.launch {
        searchValue?.let {
            searchUseCase(searchValue)
                .onSuccess { joke ->
                    Log.d("Beeeam", "$joke")
                    updateJokeSearchResult(joke)
                    showJokeLoadedToast()
                }
                .onFailure {
                    Log.d("Error", it.toString())
                }
        }
    }

    fun updateJokeSearchValue(value: String) = intent { copy( jokeSearchValue = value ) }
    private fun updateJokeSearchResult(res: SearchResult) = intent {
        copy(
            jokeSearchResultCount = res.count,
            jokeSearchResult = res.result,
        )
    }

    private fun showJokeLoadedToast() = postSideEffect(SearchSideEffect.ShowSearchResultLoaded)

    suspend fun onShowToast(msg: String) {
        intent { copy(toastMsg = msg, toastVisible = true) }
        delay(3000L)
        intent { copy(toastMsg = msg, toastVisible = false) }
    }
}