package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danish.domain.model.MovieDetail

@Composable
fun MovieDetailContent(
    movie: MovieDetail,
    onBackClick: () -> Unit,
    onWebsiteClick: () -> Unit,
    onShareClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF080D12)),
        contentPadding = PaddingValues(bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            DetailHeroSection(
                movie = movie,
                onBackClick = onBackClick
            )
        }

        item {
            DetailActionSection(
                onWebsiteClick = onWebsiteClick,
                onShareClick = onShareClick,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            GenreSection(
                genres = movie.genres,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            OverviewSection(
                overview = movie.overview,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            MovieMetadataSection(
                movie = movie,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}
