package com.danish.datastore.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException

class OnboardingPreferencesRepositoryImpl(context: Context) : OnboardingPreferencesRepository {
    private val dataStore = context.applicationContext.movieAppPreferencesDataStore
    override val isOnboardingCompleted: Flow<Boolean> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[
            PreferencesKeys.ONBOARDING_COMPLETED
        ] ?: false
    }.distinctUntilChanged()

    override suspend fun setOnboardingCompleted(isCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED] = isCompleted
        }
    }

    private object PreferencesKeys {
        val ONBOARDING_COMPLETED = booleanPreferencesKey(name = "onboarding_completed")
    }
}

private const val MOVIE_APP_PREFERENCES_NAME = "movie_app_preferences"

private val Context.movieAppPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = MOVIE_APP_PREFERENCES_NAME,
)