package com.danish.network.di

import com.danish.network.api.MovieApi
import com.danish.network.api.MovieApiImpl
import com.danish.network.client.createHttpClient
import com.danish.network.client.createNetworkJson
import com.danish.network.config.ApiNetworkConfig
import com.danish.network.config.NetworkConstants.BASE_URL
import org.koin.dsl.module

val networkModule = module {
    single {
        ApiNetworkConfig(baseUrl = BASE_URL)
    }
    single {
        createNetworkJson()
    }
    single {
        createHttpClient(config = get(), json = get())
    }
    single<MovieApi> {
        MovieApiImpl(client = get())
    }
}