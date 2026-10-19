package com.danish.shared.components

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles

@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = AppColors.TextSecondary
) {
    TextButton(onClick = onClick, modifier = modifier) {
        AppText(text = text, color = color, style = AppTextStyles.Subtitle.copy(fontWeight = FontWeight.Normal))
    }
}