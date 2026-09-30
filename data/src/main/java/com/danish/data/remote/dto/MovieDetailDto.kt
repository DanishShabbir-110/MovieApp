package com.danish.data.remote.dto

import com.danish.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("genres")
    val genres: List<GenreDto>?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("homepage")
    val homepage: String?
)

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        runtime = runtime,
        genres = genres.orEmpty().mapNotNull { it.name },
        tagline = tagline.orEmpty(),
        status = status.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        homepage = homepage
    )
}
