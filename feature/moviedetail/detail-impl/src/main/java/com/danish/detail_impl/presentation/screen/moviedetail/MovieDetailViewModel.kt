package com.danish.detail_impl.presentation.screen.moviedetail

import androidx.lifecycle.viewModelScope
import com.danish.domain.model.MovieDetail
import com.danish.domain.repository.MovieRepository
import com.danish.shared.base.BaseEffects
import com.danish.shared.base.BaseEvents
import com.danish.shared.base.BaseStates
import com.danish.shared.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : BaseViewModel<DetailState, DetailEffect, DetailEvent>(DetailState()) {
    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                val movie = movieRepository.getMovieDetails(movieId = movieId)
                updateState {
                    copy(
                        isLoading = false,
                        movie = movie,
                        errorMessage = null
                    )
                }
            } catch (ex: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = ex.message ?: "Unable to load movie details"
                    )
                }
            }
        }
    }

    override fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.BackClicked -> {
                navigateBack()
            }

            DetailEvent.Retry -> {
                getMovieDetails()
            }

            DetailEvent.WebsiteClicked -> {
                val homePage = uiState.value.movie?.homepage
                if (!homePage.isNullOrBlank()) {
                    emitEffects(
                        DetailEffect.OpenWebsite(
                            url = homePage
                        )
                    )
                } else {
                    showToast("Website not available")
                }
            }
        }
    }
}

data class DetailState(
    override val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    override val errorMessage: String? = null
) : BaseStates

sealed interface DetailEvent : BaseEvents {
    data object BackClicked : DetailEvent
    data object Retry : DetailEvent
    data object WebsiteClicked : DetailEvent
}

sealed interface DetailEffect : BaseEffects {
    data class OpenWebsite(val url: String) : DetailEffect
}