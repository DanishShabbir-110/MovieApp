package com.danish.shared.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModel<S : BaseStates, I : BaseEffects, E : BaseEvents>
    (initialState: S) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<I>()
    val effects = _effects.asSharedFlow()

    private val _baseEffects = MutableSharedFlow<BaseEffectsOfBaseViewModel>()
    val baseEffects = _baseEffects.asSharedFlow()

    abstract fun onEvent(event: E)

    protected fun updateState(reducer: S.() -> S) {
        _uiState.update { currentState ->
            currentState.reducer()
        }
    }

    protected fun emitEffects(effect: I) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    protected fun navigateBack(key: NavKey? = null) {
        emitBaseEffect(
            BaseEffectsOfBaseViewModel.NavigateBack(key)
        )
    }

    protected fun showToast(message: String) {
        emitBaseEffect(
            BaseEffectsOfBaseViewModel.ShowToast(message)
        )
    }

    private fun emitBaseEffect(
        effect: BaseEffectsOfBaseViewModel
    ) {
        viewModelScope.launch {
            _baseEffects.emit(effect)
        }
    }
}