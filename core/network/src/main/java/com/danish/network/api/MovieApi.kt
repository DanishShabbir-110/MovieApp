package com.danish.network.api

import com.danish.network.dto.MovieDetailDto
import com.danish.network.dto.MovieResponseDto

interface MovieApi {
    suspend fun getPopularMovies(
        apiKey: String,
        page: Int = DEFAULT_PAGE,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun getTrendingMovies(
        apiKey: String,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun searchMovies(
        apiKey: String,
        query: String,
        page: Int = DEFAULT_PAGE,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String = DEFAULT_LANGUAGE
    ): MovieDetailDto

    private companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_LANGUAGE = "en-US"
    }
}