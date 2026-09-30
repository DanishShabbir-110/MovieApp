package com.danish.detail_impl.presentation.screen.moviedetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import com.danish.detail_impl.presentation.components.DetailContent
import com.danish.navigation.Navigator
import com.danish.shared.base.BaseEffectsOfBaseViewModel
import com.danish.shared.base.BaseScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun DetailScreen(
    state: DetailState,
    effects: Flow<DetailEffect>,
    baseEffect: Flow<BaseEffectsOfBaseViewModel>,
    onEvent: (DetailEvent) -> Unit,
    navigator: Navigator
) {
    val uriHandler = LocalUriHandler.current
    BaseScreen(
        state = state,
        effects = effects,
        baseEffects = baseEffect,
        onEvent = onEvent,
        navigator = navigator,
        onEffect = { effect ->
            when (effect) {
                is DetailEffect.OpenWebsite -> {
                    uriHandler.openUri(effect.url)
                }
            }
        }
    ) { uiState, onEvent ->
        DetailContent(
            state = uiState,
            onEvent = onEvent
        )
    }
}