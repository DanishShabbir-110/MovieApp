package com.danish.network.dto

import kotlinx.serialization.SerialName

data class GenreDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String?
)
