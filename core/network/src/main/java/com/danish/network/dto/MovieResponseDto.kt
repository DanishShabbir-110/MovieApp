package com.danish.network.dto

import kotlinx.serialization.SerialName


data class MovieResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)