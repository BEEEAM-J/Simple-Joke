package com.beeeam.feature

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.beeeam.domain.model.Category
import com.beeeam.domain.usecase.GetCategoryUseCase
import com.beeeam.domain.usecase.GetJokeUseCase
import com.beeeam.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getJokeUseCase: GetJokeUseCase,
): BaseViewModel<MainState, MainSideEffect>(
    MainState()
) {
    fun getCategory() = viewModelScope.launch {
        getCategoryUseCase()
            .onSuccess { category ->
                updateCategoryValue(category)
            }
            .onFailure {
                Log.d("Error", it.toString())
            }
    }

    fun getJoke(category: String) = viewModelScope.launch {
        getJokeUseCase(category)
            .onSuccess { joke ->
                updateJokeValue(joke.joke)
                showJokeLoadedToast()
                hideCategoryDropDown()
            }
            .onFailure {
                Log.d("Error", it.toString())
            }
    }

    private fun updateCategoryValue(category: Category) = intent { copy(category = category) }

    private fun updateJokeValue(value: String) = intent { copy(joke = value) }

    fun updateJokeSearchValue(value: String) = intent { copy(jokeSearchValue = value) }

    fun showCategoryDropDown() = intent { copy(isDropDownExpanded = true) }
    fun hideCategoryDropDown() = intent { copy(isDropDownExpanded = false) }

    suspend fun onShowToast(msg: String) {
        intent { copy(toastMsg = msg, toastVisible = true) }
        delay(3000L)
        intent { copy(toastMsg = msg, toastVisible = false) }
    }

    private fun showJokeLoadedToast() = postSideEffect(MainSideEffect.ShowJokeLoaded)
}