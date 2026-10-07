package com.danish.onboarding_impl.presentation.screen

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.onboarding_impl.presentation.navigation.OnBoardingRoutes
import com.danish.onboarding_impl.presentation.screen.onboarding.OnboardingEffect
import com.danish.onboarding_impl.presentation.screen.onboarding.OnboardingScreen
import com.danish.onboarding_impl.presentation.screen.onboarding.OnboardingViewModel
import com.danish.shared.base.BaseScreen
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.onBoardingEntry(
    navigator: Navigator,
    onBoardingCompleted: () -> Unit
) {
    entry<OnBoardingRoutes.OnBoarding> {
        val viewModel = koinViewModel<OnboardingViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        BaseScreen(
            state = state,
            effects = viewModel.effects,
            baseEffects = viewModel.baseEffects,
            onEvent = viewModel::onEvent,
            navigator = navigator,
            onEffect = { effect ->
                when (effect) {
                    OnboardingEffect.Completed -> {
                        onBoardingCompleted()
                    }
                }
            }
        ) { currentState, currentOnEvent ->
            OnboardingScreen(
                state = currentState,
                onEvent = currentOnEvent
            )
        }
    }
}