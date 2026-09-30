package com.danish.detail_impl.di

import com.danish.detail_api.MovieDetailFeatureApi
import com.danish.detail_impl.presentation.navigation.MovieDetailFeatureApiImpl
import com.danish.detail_impl.presentation.screen.moviedetail.MovieDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    single<MovieDetailFeatureApi> {
        MovieDetailFeatureApiImpl()
    }

    viewModel { params ->
        MovieDetailViewModel(
            movieId = params.get(),
            movieRepository = get()
        )
    }
}