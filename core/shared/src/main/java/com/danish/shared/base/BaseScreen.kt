package com.danish.shared.base

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.danish.navigation.Navigator
import com.danish.shared.components.ErrorDialog
import com.danish.shared.components.LoadingDialog
import kotlinx.coroutines.launch

@Composable
fun <S : BaseStates, E : BaseEvents, I : BaseEffects> BaseScreen(
    viewModel: BaseViewModel<S, I, E>,
    navigator: Navigator,
    onEffect: suspend (I) -> Unit = {},
    content: @Composable (paddingValues: PaddingValues, state: S, onEvent: (E) -> Unit) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.effects.collect { effect ->
                        onEffect(effect)
                    }
                }

                launch {
                    viewModel.baseEffects.collect { effect ->
                        when (effect) {
                            is BaseEffectsOfBaseViewModel.NavigateTo -> {
                                navigator.navigateTo(effect.navKey)
                            }

                            is BaseEffectsOfBaseViewModel.NavigateAndPopStack -> {
                                navigator.navigateAndPopStack(effect.navKey)
                            }

                            is BaseEffectsOfBaseViewModel.NavigateBack -> {
                                navigator.navigateBack(effect.navKey)
                            }

                            is BaseEffectsOfBaseViewModel.ShowToast -> {
                                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
            ErrorDialog(state.value.errorMessage)
        }
    ) { paddingValues ->
        content(paddingValues, state.value, viewModel::onEvent)
        if (state.value.isLoading) LoadingDialog()
    }
}