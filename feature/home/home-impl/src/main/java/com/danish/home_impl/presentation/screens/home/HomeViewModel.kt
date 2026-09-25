package com.danish.home_impl.presentation.screens.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.danish.domain.model.Movie
import com.danish.domain.repository.MovieRepository
import com.danish.shared.base.BaseEffects
import com.danish.shared.base.BaseEvents
import com.danish.shared.base.BaseStates
import com.danish.shared.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) :
    BaseViewModel<State, Effect, Event>(State()) {

    val popularMovies=repository.getPopularMovies().cachedIn(viewModelScope)
    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch{
            updateState {
                copy(isLoading = true,errorMessage = null)
            }
            repository.getTrendingMovies().catch { exception ->
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = exception.message?: "Unable to load trending movies"
                    )
                }
            }.collect {movies ->
                updateState {
                    copy(
                        isLoading=false,
                        errorMessage = null,
                        trendingMovies=movies
                    )
                }
            }
        }
    }

    override fun onEvent(event: Event) {
        when(event){
            is Event.MovieClicked ->{

            }
            Event.RetryTrending -> getTrendingMovies()
        }
    }

}

data class State(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trendingMovies: List<Movie> = emptyList()
) : BaseStates

sealed interface Event : BaseEvents {
    data class MovieClicked(val movieId: Int) : Event
    data object RetryTrending : Event
}

sealed interface Effect : BaseEffects