package com.danish.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles

@Composable
fun TagChip(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = AppColors.ChipBackground,
    contentColor: Color = AppColors.TextSecondary,
    style: TextStyle = AppTextStyles.Label,
    shape: Shape = AppShapes.Small,
    padding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 3.dp)
) {
    Surface(modifier = modifier, shape = shape, color = containerColor) {
        AppText(
            text = text,
            style = style,
            color = contentColor,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun PillChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = AppColors.SurfaceVariant,
        border = BorderStroke(1.dp, AppColors.Border)
    ) {
        AppText(
            text = text,
            style = AppTextStyles.BodySmall.copy(fontWeight = FontWeight.Medium),
            color = AppColors.TextPrimary.copy(alpha = 0.90f),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}