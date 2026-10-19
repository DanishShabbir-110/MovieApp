package com.danish.search_impl.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.navigation.Navigator
import com.danish.search_impl.presentation.components.SearchResults
import com.danish.shared.base.BaseEffectsOfBaseViewModel
import com.danish.shared.base.BaseScreen
import com.danish.shared.components.AppSearchField
import com.danish.shared.components.AppText
import com.danish.shared.components.StateView
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchScreen(
    state: SearchState,
    searchMovies: LazyPagingItems<Movie>,
    effects: Flow<SearchEffect>,
    baseEffects: Flow<BaseEffectsOfBaseViewModel>,
    onEvent: (SearchEvent) -> Unit,
    navigator: Navigator,
    onEffect: suspend (SearchEffect) -> Unit
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
                .background(AppColors.Background)
                .statusBarsPadding()
        ) {
            AppText(
                text = "Search",
                style = AppTextStyles.Display,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppSearchField(
                query = uiState.query,
                onQueryChange = { event(SearchEvent.QueryChanged(it)) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (uiState.query.isBlank()) {
                StateView(
                    icon = Icons.Rounded.Search,
                    title = "Search movies",
                    message = "Find you favourite movies",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                )
            } else {
                AppText(
                    text = "Search Results",
                    style = AppTextStyles.Title.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(12.dp))

                SearchResults(
                    movies = searchMovies,
                    onMovieClick = { event(SearchEvent.MovieClicked(it)) }
                )
            }
        }
    }
}
