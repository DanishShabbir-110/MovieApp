package com.danish.network.dto

import com.danish.domain.model.MovieDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("genres")
    val genres: List<GenreDto>?,
    @SerialName("tagline")
    val tagline: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("homepage")
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
