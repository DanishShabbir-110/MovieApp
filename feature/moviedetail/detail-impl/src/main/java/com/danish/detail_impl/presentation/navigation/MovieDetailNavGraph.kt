package com.danish.detail_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.detail_impl.presentation.screen.movieDetailEntry
import com.danish.navigation.Navigator

fun EntryProviderScope<NavKey>.addMovieDetailScreen(navigator: Navigator){
    movieDetailEntry(navigator=navigator)
}