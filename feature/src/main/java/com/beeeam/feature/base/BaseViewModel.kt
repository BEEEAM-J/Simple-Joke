package com.beeeam.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UI_STATE: UiState, SIDE_EFFECT: SideEffect>(
    initialState: UI_STATE
): ViewModel() {

    // 앱 내의 상태
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    // 일회성 이벤트 처리 하기 위한 SideEffect
    private val _sideEffect: Channel<SIDE_EFFECT> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    protected val currentState: UI_STATE
        get() = _uiState.value

    // 상태 변경하기 위한 intent 함수
    protected fun intent(reduce: UI_STATE.() -> UI_STATE) {
        val state = currentState.reduce()
        _uiState.update{ state }
    }

    // SideEffect 동작 전달 함수
    protected fun postSideEffect(vararg builder: SIDE_EFFECT) {
        for (effect in builder) {
            viewModelScope.launch { _sideEffect.send(effect) }
        }
    }
}