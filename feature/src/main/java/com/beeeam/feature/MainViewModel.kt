package com.beeeam.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beeeam.domain.usecase.GetCategoryUseCase
import com.beeeam.domain.usecase.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getJokeUseCase: GetJokeUseCase,
): ViewModel() {
    fun loadCategory() {
        viewModelScope.launch {
            getCategoryUseCase()
                .onSuccess {
                    Log.d("test", it.toString())
                }
                .onFailure {
                    Log.d("test", it.toString())
                }
        }
    }

    fun loadJoke(category: String) {
        viewModelScope.launch {
            getJokeUseCase(category)
                .onSuccess {
                    Log.d("test", it.toString())
                }
                .onFailure {
                    Log.d("test", it.toString())
                }
        }
    }
}