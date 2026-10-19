package com.danish.shared.util

enum class MovieImageSize(val path:String){
    W342("w342"),
    W500("w500"),
    W780("w780"),
    Original("original")
}

fun movieImageUrl(path: String?, size: MovieImageSize = MovieImageSize.W500): String? =
    path?.let { "https://image.tmdb.org/t/p/${size.path}$it" }