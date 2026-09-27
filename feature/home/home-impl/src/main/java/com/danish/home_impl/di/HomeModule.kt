package com.danish.home_impl.di

import com.danish.home_api.HomeFeatureApi
import com.danish.home_impl.presentation.navigation.HomeFeatureApiImpl
import com.danish.home_impl.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule= module{
    single <HomeFeatureApi> { HomeFeatureApiImpl() }
    viewModelOf(::HomeViewModel)
}