package com.danish.onboarding_impl.presentation.model

import com.danish.onboarding_impl.R

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Discover Amazing Movies",
        description = "Explore trending and popular movies all in one place.",
        imageRes = R.drawable.onboarding1
    ),
    OnboardingPage(
        title = "Search Your Favorites",
        description = "Find the movies you love quickly with powerful search.",
        imageRes = R.drawable.onboarding2
    ),
    OnboardingPage(
        title = "Explore Movie Details",
        description = "Discover ratings, genres, story details and more.",
        imageRes = R.drawable.onboarding3
    )
)
