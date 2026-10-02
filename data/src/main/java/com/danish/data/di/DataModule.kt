package com.danish.data.di

import com.danish.data.BuildConfig
import com.danish.data.repository.MovieRepositoryImpl
import com.danish.domain.repository.MovieRepository
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieApi = get(),
            apiKey = BuildConfig.API_KEY
        )
    }
}