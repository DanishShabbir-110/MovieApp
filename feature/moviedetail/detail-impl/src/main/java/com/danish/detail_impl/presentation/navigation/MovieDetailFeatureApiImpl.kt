package com.danish.detail_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.detail_api.MovieDetailFeatureApi
import com.danish.navigation.Navigator

class MovieDetailFeatureApiImpl: MovieDetailFeatureApi {
    override fun movieDetailEntry(movieId: Int): NavKey {
        return MovieDetailRoutes.MovieDetail(movieId)
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {
        provider.addMovieDetailScreen(navigator=navigator)
    }
}