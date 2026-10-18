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
import kotlinx.coroutines.flow.map

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel(
    private val repository: MovieRepository
) : BaseViewModel<SearchState, SearchEffect, SearchEvent>(SearchState()) {
    val searchMovies = uiState.map { it.query.trim() }.distinctUntilChanged()
        .debounce { query -> if (query.isBlank()) 0L else 500L }
        .flatMapLatest { query ->
            when {
                query.length < MIN_QUERY_LENGTH -> flowOf(PagingData.empty())
                else -> repository.searchMovies(query)
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

    private companion object {
        const val MIN_QUERY_LENGTH = 2
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