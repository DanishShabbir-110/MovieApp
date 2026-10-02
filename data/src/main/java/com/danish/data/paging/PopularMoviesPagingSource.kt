package com.danish.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danish.domain.model.Movie
import com.danish.network.api.MovieApi
import com.danish.network.dto.toDomain

class PopularMoviesPagingSource(
    private val movieApi: MovieApi,
    private val apiKey: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)

        return anchorPage?.prevKey?.plus(1)
            ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = movieApi.getPopularMovies(apiKey = apiKey, page = page)
            val movies = response.results.map { movieDto -> movieDto.toDomain() }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (movies.isEmpty() || page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val STARTING_PAGE = 1
    }
}