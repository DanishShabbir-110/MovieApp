package com.danish.search_impl.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.navigation.Navigator
import com.danish.search_impl.presentation.components.EmptySearchContent
import com.danish.search_impl.presentation.components.SearchBar
import com.danish.search_impl.presentation.components.SearchResults
import com.danish.shared.base.BaseEffectsOfBaseViewModel
import com.danish.shared.base.BaseScreen
import kotlinx.coroutines.flow.Flow

val SearchBackground = Color(0xFF080D12)
val SearchFieldBackground = Color(0xFF121820)
val CineHubRed = Color(0xFFFF3158)
val SecondaryText = Color(0xFFADB5BD)
val RatingYellow = Color(0xFFFFC107)

@Composable
fun SearchScreen(
    state: SearchState,
    searchMovies: LazyPagingItems<Movie>,
    effects: Flow<SearchEffect>,
    baseEffects: Flow<BaseEffectsOfBaseViewModel>,
    onEvent: (SearchEvent) -> Unit,
    navigator: Navigator,
    onEffect: suspend (SearchEffect)-> Unit
) {
    BaseScreen(
        state = state,
        effects = effects,
        baseEffects = baseEffects,
        onEvent = onEvent,
        navigator = navigator,
        onEffect = onEffect
    ) { uiState, event ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SearchBackground)
                .statusBarsPadding()
        ) {

            Text(
                text = "Search",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp, end = 16.dp, top = 16.dp
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            SearchBar(
                query = uiState.query, onQueryChange = { query ->
                    event(
                        SearchEvent.QueryChanged(query)
                    )
                })

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            if (uiState.query.isBlank()) {
                EmptySearchContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                )

            } else {

                Text(
                    text = "Search Results",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                SearchResults(
                    movies = searchMovies,
                    onMovieClick = { movieId ->
                        event(
                            SearchEvent.MovieClicked(movieId)
                        )
                    }
                )
            }
        }
    }
}

