package com.danish.shared.base

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.danish.navigation.Navigator
import com.danish.shared.components.LoadingDialog
import kotlinx.coroutines.flow.Flow

@Composable
fun <S : BaseStates, E : BaseEvents, I : BaseEffects> BaseScreen(
    state: S,
    effects: Flow<I>,
    baseEffects: Flow<BaseEffectsOfBaseViewModel>,
    onEvent: (E) -> Unit,
    navigator: Navigator,
    onEffect: suspend (I) -> Unit = {},
    content: @Composable (state: S, onEvent: (E) -> Unit) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(effects, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effects.collect { effect ->
                onEffect(effect)
            }
        }
    }
    LaunchedEffect(baseEffects, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            baseEffects.collect { effect ->
                when (effect) {
                    is BaseEffectsOfBaseViewModel.NavigateAndPopStack -> {
                        navigator.navigateAndPopStack(effect.navKey)
                    }

                    is BaseEffectsOfBaseViewModel.NavigateBack -> {
                        navigator.navigateBack(effect.navKey)
                    }

                    is BaseEffectsOfBaseViewModel.NavigateTo -> {
                        navigator.navigateTo(effect.navKey)
                    }

                    is BaseEffectsOfBaseViewModel.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        content(state, onEvent)
        if (state.isLoading) LoadingDialog()
    }
}