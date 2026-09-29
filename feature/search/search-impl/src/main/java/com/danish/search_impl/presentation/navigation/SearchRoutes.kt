package com.danish.search_impl.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchRoutes: NavKey {
    @Serializable
    data object Search : SearchRoutes
}