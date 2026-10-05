package com.danish.network.api

import com.danish.network.dto.MovieDetailDto
import com.danish.network.dto.MovieResponseDto

interface MovieApi {
    suspend fun getPopularMovies(
        apiKey: String=API_KEY,
        page: Int = DEFAULT_PAGE,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun getTrendingMovies(
        apiKey: String=API_KEY,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun searchMovies(
        apiKey: String=API_KEY,
        query: String,
        page: Int = DEFAULT_PAGE,
        language: String = DEFAULT_LANGUAGE
    ): MovieResponseDto

    suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String=API_KEY,
        language: String = DEFAULT_LANGUAGE
    ): MovieDetailDto

    private companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_LANGUAGE = "en-US"
        const val API_KEY= "api_key"
    }
}