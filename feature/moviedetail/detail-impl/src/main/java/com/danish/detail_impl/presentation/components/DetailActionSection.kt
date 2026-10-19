package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.shared.components.AppButton

@Composable
fun DetailActionSection(
    onWebsiteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppButton(
            text = "Website",
            icon = Icons.Outlined.Language,
            onClick = onWebsiteClick,
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        )
    }
}