package com.danish.onboarding_impl.di

import com.danish.onboarding_api.OnBoardingFeatureApi
import com.danish.onboarding_impl.presentation.navigation.OnBoardingFeatureApiImpl
import com.danish.onboarding_impl.presentation.screen.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule= module {
    single<OnBoardingFeatureApi> {
        OnBoardingFeatureApiImpl()
    }
    viewModelOf(::OnboardingViewModel)
}