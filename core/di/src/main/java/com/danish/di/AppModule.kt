package com.danish.di

import com.danish.data.di.dataModule
import com.danish.network.di.networkModule
import org.koin.dsl.module

val appModules= module {
    includes(networkModule)
    includes(dataModule)
}