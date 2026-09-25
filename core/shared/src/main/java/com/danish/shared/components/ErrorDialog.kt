package com.danish.shared.components

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun ErrorDialog(message: String?) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(message) {
        if (!message.isNullOrBlank()) snackBarHostState.showSnackbar(message)
    }
    SnackbarHost(hostState = snackBarHostState)
}