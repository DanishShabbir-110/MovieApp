package com.danish.detail_impl.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MovieDetailRoutes : NavKey {
    @Serializable
    data class MovieDetail(val movieId: Int) : MovieDetailRoutes
}