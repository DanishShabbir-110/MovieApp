package com.danish.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.util.formatRating

enum class RatingBadgeSize(
    val star: Dp,
    val text: TextUnit,
    val gap: Dp,
    val horizontalPadding: Dp
) {
    Small(star = 11.dp, text = 10.sp, gap = 3.dp, horizontalPadding = 6.dp),
    Medium(star = 13.dp, text = 12.sp, gap = 4.dp, horizontalPadding = 8.dp),
    Large(star = 15.dp, text = 12.sp, gap = 4.dp, horizontalPadding = 8.dp)
}

@Composable
fun RatingBadge(
    rating: Double,
    modifier: Modifier = Modifier,
    size: RatingBadgeSize = RatingBadgeSize.Small,
    background: Color? = null,
    fontWeight: FontWeight = FontWeight.Bold
) {
    val rowModifier = if (background != null) {
        modifier
            .background(color = background, shape = AppShapes.Small)
            .padding(horizontal = size.horizontalPadding, vertical = 3.dp)
    } else {
        modifier
    }

    Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = AppColors.Rating,
            modifier = Modifier.size(size.star)
        )
        Spacer(Modifier.width(size.gap))
        AppText(
            text = formatRating(rating),
            style = TextStyle(fontSize = size.text, fontWeight = fontWeight)
        )
    }
}