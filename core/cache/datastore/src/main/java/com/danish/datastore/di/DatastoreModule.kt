package com.danish.datastore.di

import com.danish.datastore.core.OnboardingPreferencesRepository
import com.danish.datastore.core.OnboardingPreferencesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val datastoreModule = module {
    single<OnboardingPreferencesRepository> {
        OnboardingPreferencesRepositoryImpl(context = androidContext())
    }
}