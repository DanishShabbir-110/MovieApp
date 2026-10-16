package com.danish.search_impl.presentation.screens.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.danish.domain.repository.MovieRepository
import com.danish.shared.base.BaseEffects
import com.danish.shared.base.BaseEvents
import com.danish.shared.base.BaseStates
import com.danish.shared.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val repository: MovieRepository
) : BaseViewModel<SearchState, SearchEffect, SearchEvent>(SearchState()) {
    val searchMovies =
        uiState.debounce(500L.milliseconds).distinctUntilChanged { old, new ->
            old.query == new.query
        }.flatMapLatest { state ->
            val trimmedQuery = state.query.trim()
            if (trimmedQuery.isBlank()) {
                flowOf(PagingData.empty())
            } else {
                repository.searchMovies(trimmedQuery)
            }
        }.cachedIn(viewModelScope)

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.MovieClicked -> {
                emitEffects(SearchEffect.OpenMovieDetail(event.movieId))
            }

            is SearchEvent.QueryChanged -> {
                updateState { copy(query = event.query) }
            }
        }
    }

}

data class SearchState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val query: String = ""
) : BaseStates

sealed class SearchEvent : BaseEvents {
    data class QueryChanged(val query: String) : SearchEvent()
    data class MovieClicked(val movieId: Int) : SearchEvent()
}

sealed class SearchEffect : BaseEffects {
    data class OpenMovieDetail(val movieId: Int) : SearchEffect()
}