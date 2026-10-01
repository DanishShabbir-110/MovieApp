package com.danish.di

import com.danish.data.di.dataModule
import com.danish.datastore.di.datastoreModule
import com.danish.detail_impl.di.detailModule
import com.danish.home_impl.di.homeModule
import com.danish.network.di.networkModule
import com.danish.onboarding_impl.di.onboardingModule
import com.danish.search_impl.di.searchModule
import com.example.main_impl.di.mainModule
import org.koin.dsl.module

val appModules = module {
    includes(
        networkModule,
        dataModule,
        homeModule,
        mainModule,
        searchModule,
        detailModule,
        onboardingModule,
        datastoreModule
    )
}