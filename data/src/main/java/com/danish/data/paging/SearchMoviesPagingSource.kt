package com.danish.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danish.data.remote.api.MovieApi
import com.danish.data.remote.dto.toDomain
import com.danish.domain.model.Movie

class SearchMoviesPagingSource(
    private val movieApi: MovieApi,
    private val apiKey: String,
    private val query: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)

        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = movieApi.searchMovies(
                apiKey = apiKey,
                query = query,
                page = page
            )

            val movies = response.results.map { movieDto ->
                movieDto.toDomain()
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (movies.isEmpty() || page >= response.totalPages) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
    private companion object{
        const val STARTING_PAGE = 1
    }
}