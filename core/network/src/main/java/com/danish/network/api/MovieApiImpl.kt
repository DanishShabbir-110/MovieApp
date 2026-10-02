package com.danish.network.api

import com.danish.network.dto.MovieDetailDto
import com.danish.network.dto.MovieResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiImpl(private val client: HttpClient) : MovieApi {
    override suspend fun getPopularMovies(
        apiKey: String,
        page: Int,
        language: String
    ): MovieResponseDto {
        return requestMoviePage(
            endpoint = GET_POPULAR_MOVIES,
            language = language,
            page = page,
            apiKey = apiKey
        )
    }

    override suspend fun getTrendingMovies(
        apiKey: String,
        language: String
    ): MovieResponseDto {
        return requestMoviePage(
            endpoint = GET_TRENDING_MOVIES,
            language = language,
            apiKey = apiKey
        )
    }

    override suspend fun searchMovies(
        apiKey: String,
        query: String,
        page: Int,
        language: String
    ): MovieResponseDto {
        val searchQuery = query.trim()
        require(searchQuery.isNotBlank()) {
            "Search query must not be blank"
        }
        require(page > 0) {
            "Page must be greater than zero."
        }
        require(apiKey.isNotBlank()) {
            "Api key must not be blank"
        }
        return client.get(GET_SEARCH_MOVIES) {
            parameter(API_KEY_QUERY_PARAMETER, apiKey)
            parameter(SEARCH_QUERY_PARAMETER, searchQuery)
            parameter(PAGE_QUERY_PARAMETER, page)
            parameter(LANGUAGE_QUERY_PARAMETER, language)
        }.body()
    }

    override suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): MovieDetailDto {
        require(movieId > 0) {
            "Movie id must be greater than zero."
        }
        require(apiKey.isNotBlank()) {
            "Api key must not be blank"
        }
        return client.get("$GET_MOVIE_DETAILS/$movieId") {
            parameter(API_KEY_QUERY_PARAMETER, apiKey)
            parameter(LANGUAGE_QUERY_PARAMETER, language)
        }.body()
    }

    private suspend fun requestMoviePage(
        endpoint: String,
        language: String,
        page: Int? = null,
        apiKey: String
    ): MovieResponseDto {
        require(language.isNotBlank()) {
            "Language must not be blank"
        }
        require(page == null || page > 0) {
            "Page must be greater than 0"
        }
        require(apiKey.isNotBlank()) {
            "Api key must not be blank"
        }
        return client.get(endpoint) {
            parameter(
                key = LANGUAGE_QUERY_PARAMETER,
                value = language
            )
            parameter(
                key = API_KEY_QUERY_PARAMETER,
                value = apiKey
            )
            page?.let {
                parameter(
                    key = PAGE_QUERY_PARAMETER,
                    value = page
                )
            }
        }.body()
    }

    private companion object {
        const val GET_POPULAR_MOVIES = "movie/popular"
        const val GET_TRENDING_MOVIES = "trending/movie/day"
        const val GET_SEARCH_MOVIES = "search/movie"
        const val GET_MOVIE_DETAILS = "movie"
        const val LANGUAGE_QUERY_PARAMETER = "language"
        const val PAGE_QUERY_PARAMETER = "page"
        const val API_KEY_QUERY_PARAMETER = "api_key"
        const val SEARCH_QUERY_PARAMETER = "query"
    }
}