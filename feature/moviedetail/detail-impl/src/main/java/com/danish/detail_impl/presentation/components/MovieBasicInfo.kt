package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.danish.shared.components.AppText
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.formatRuntime
import com.danish.shared.util.releaseYear

@Composable
fun MovieBasicInfo(
    releaseDate: String,
    runtime: Int?
) {
    val year = releaseYear(releaseDate)
    val infoColor = AppColors.TextPrimary.copy(alpha = 0.70f)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (year.isNotBlank()) {
            AppText(text = year, style = AppTextStyles.BodySmall, color = infoColor)
        }

        if (year.isNotBlank() && runtime != null) {
            AppText(
                text = "  |  ",
                style = AppTextStyles.BodySmall,
                color = AppColors.TextPrimary.copy(alpha = 0.45f)
            )
        }
        runtime?.let {
            AppText(text = formatRuntime(it), style = AppTextStyles.BodySmall, color = infoColor)
        }
    }
}