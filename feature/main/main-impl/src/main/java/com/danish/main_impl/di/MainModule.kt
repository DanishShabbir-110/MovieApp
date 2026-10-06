package com.danish.main_impl.di

import com.danish.main_api.MainFeatureApi
import com.danish.main_impl.presentation.navigation.MainFeatureApiImpl
import org.koin.dsl.module

val mainModule= module {
    single<MainFeatureApi> {
        MainFeatureApiImpl()
    }
}