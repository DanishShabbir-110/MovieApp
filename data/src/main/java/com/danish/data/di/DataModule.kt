package com.danish.data.di

import com.danish.data.remote.api.MovieApi
import com.danish.data.repository.MovieRepositoryImpl
import com.danish.domain.repository.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import com.danish.data.BuildConfig

val dataModule=module{
    single<MovieApi> {
        get<Retrofit>().create(MovieApi::class.java)
    }

    single<MovieRepository> {
        MovieRepositoryImpl(
            movieApi = get(),
            apiKey = BuildConfig.API_KEY
        )
    }
}