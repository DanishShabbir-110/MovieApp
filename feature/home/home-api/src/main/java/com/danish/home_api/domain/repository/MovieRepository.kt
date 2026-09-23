package com.danish.home_api.domain.repository

import com.danish.home_api.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovies(): Flow<List<Movie>>
    fun getPopularMovies(): Flow<List<Movie>>
}