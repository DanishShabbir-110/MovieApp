package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.shared.components.AppText
import com.danish.shared.components.SectionTitle
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles

@Composable
fun OverviewSection(
    overview: String,
    modifier: Modifier = Modifier
) {
    if (overview.isBlank()) return

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SectionTitle(title = "Overview")
        AppText(
            text = overview,
            style = AppTextStyles.Body,
            color = AppColors.TextPrimary.copy(alpha = 0.80f),
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
