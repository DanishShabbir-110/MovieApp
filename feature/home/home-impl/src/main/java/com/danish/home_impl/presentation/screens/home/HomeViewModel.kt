package com.danish.home_impl.presentation.screens.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.danish.domain.model.Movie
import com.danish.domain.repository.MovieRepository
import com.danish.shared.base.BaseEffects
import com.danish.shared.base.BaseEvents
import com.danish.shared.base.BaseStates
import com.danish.shared.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) :
    BaseViewModel<HomeState, HomeEffect, HomeEvent>(HomeState()) {

    val popularMovies = repository.getPopularMovies().cachedIn(viewModelScope)

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            updateState {
                copy(isLoading = true, errorMessage = null)
            }
            try {
                val movies = repository.getTrendingMovies()
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = null,
                        trendingMovies = movies
                    )
                }
            } catch (ex: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = ex.message ?: "Unable to load trending movies"
                    )
                }
            }
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.MovieClicked -> {
                emitEffects(HomeEffect.OpenMovieDetail(movieId = event.movieId))
            }

            HomeEvent.RetryTrending -> getTrendingMovies()
        }
    }

}

data class HomeState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trendingMovies: List<Movie> = emptyList()
) : BaseStates

sealed interface HomeEvent : BaseEvents {
    data class MovieClicked(val movieId: Int) : HomeEvent
    data object RetryTrending : HomeEvent
}

sealed interface HomeEffect : BaseEffects {
    data class OpenMovieDetail(val movieId: Int) : HomeEffect
}