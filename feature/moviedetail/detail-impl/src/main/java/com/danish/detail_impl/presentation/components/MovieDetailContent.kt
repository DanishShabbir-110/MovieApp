package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.domain.model.MovieDetail
import com.danish.shared.theme.AppColors

@Composable
fun MovieDetailContent(
    movie: MovieDetail,
    onBackClick: () -> Unit,
    onWebsiteClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background),
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
