package com.danish.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danish.data.paging.PopularMoviesPagingSource
import com.danish.data.paging.SearchMoviesPagingSource
import com.danish.domain.model.Movie
import com.danish.domain.model.MovieDetail
import com.danish.domain.repository.MovieRepository
import com.danish.network.api.MovieApi
import com.danish.network.dto.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val apiKey: String
) : MovieRepository {
    override fun getTrendingMovies(): Flow<List<Movie>> {
        return flow {
            val response = movieApi.getTrendingMovies(apiKey = apiKey)
            emit(response.results.map { movieDto -> movieDto.toDomain() })
        }
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    movieApi = movieApi,
                    apiKey = apiKey
                )
            }
        ).flow
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMoviesPagingSource(
                    movieApi = movieApi,
                    apiKey = apiKey,
                    query = query
                )
            }
        ).flow
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieDetail> {
        return flow {
            val response = movieApi.getMovieDetails(movieId = movieId, apiKey = apiKey)
            emit(response.toDomain())
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}