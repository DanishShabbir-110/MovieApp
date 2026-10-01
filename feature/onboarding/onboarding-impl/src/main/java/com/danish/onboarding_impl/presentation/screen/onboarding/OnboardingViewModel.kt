package com.danish.onboarding_impl.presentation.screen.onboarding

import androidx.lifecycle.viewModelScope
import com.danish.datastore.core.OnboardingPreferencesRepository
import com.danish.shared.base.BaseEffects
import com.danish.shared.base.BaseEvents
import com.danish.shared.base.BaseStates
import com.danish.shared.base.BaseViewModel
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val repository: OnboardingPreferencesRepository
) : BaseViewModel<OnboardingState, OnboardingEffect, OnboardingEvent>(OnboardingState()) {
    override fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.Completed -> completeOnboarding()
        }
    }

    private fun completeOnboarding() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            runCatching {
                repository.setOnboardingCompleted(isCompleted = true)
            }.onSuccess {
                updateState { copy(isLoading = false) }
                emitEffects(OnboardingEffect.Completed)
            }.onFailure {
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage=it.message?: "Unable to complete onboarding"
                    )
                }
                showToast(
                    it.message ?: "Unable to complete onboarding"
                )
            }
        }
    }
}

data class OnboardingState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseStates

sealed interface OnboardingEvent : BaseEvents {
    data object Completed : OnboardingEvent
}

sealed interface OnboardingEffect : BaseEffects {
    data object Completed : OnboardingEffect
}