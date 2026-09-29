package com.danish.search_impl.di

import com.danish.search_api.SearchFeatureApi
import com.danish.search_impl.presentation.navigation.SearchFeatureApiImpl
import com.danish.search_impl.presentation.screens.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule= module {
    single<SearchFeatureApi> {
        SearchFeatureApiImpl()
    }

    viewModelOf(::SearchViewModel)
}