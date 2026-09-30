package com.danish.domain.repository

import androidx.paging.PagingData
import com.danish.domain.model.Movie
import com.danish.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovies(): Flow<List<Movie>>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetail>
}