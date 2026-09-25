package com.danish.data.remote.api

import com.danish.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto
}