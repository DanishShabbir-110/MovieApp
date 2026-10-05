package com.danish.domain.repository

import androidx.paging.PagingData
import com.danish.domain.model.Movie
import com.danish.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(): List<Movie>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): MovieDetail
}