package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.shared.components.AppBackButton
import com.danish.shared.components.ErrorView
import com.danish.shared.theme.AppColors

@Composable
fun DetailErrorContent(
    message: String,
    onRetryClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        AppBackButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp)
        )
        ErrorView(
            title = "Unable to load movie",
            message = message,
            onRetry = onRetryClick,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

