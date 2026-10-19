package com.danish.shared.util

import java.util.Locale

fun formatRuntime(runtime:Int): String{
    val hours=runtime/60
    val minutes=runtime%60
    return when {
        hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
        hours > 0 -> "${hours}h"
        else -> "${minutes}m"
    }
}

fun formatVoteCount(voteCount: Int): String {
    return when {
        voteCount >= 1_000_000 -> {
            String.format(
                Locale.US,
                "%.1fM",
                voteCount / 1_000_000.0
            )
        }

        voteCount >= 1_000 -> {
            String.format(
                Locale.US,
                "%.1fK",
                voteCount / 1_000.0
            )
        }

        else -> voteCount.toString()
    }
}
fun releaseYear(releaseDate: String): String =
    if (releaseDate.length >= 4) releaseDate.take(4) else ""