package com.danish.datastore.core

import kotlinx.coroutines.flow.Flow

interface OnboardingPreferencesRepository {
    val isOnboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(isCompleted: Boolean)
}