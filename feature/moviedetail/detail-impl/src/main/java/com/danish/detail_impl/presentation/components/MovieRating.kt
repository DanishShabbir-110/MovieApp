package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.shared.components.AppText
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.formatRating
import com.danish.shared.util.formatVoteCount

@Composable
fun MovieRating(
    rating: Double,
    voteCount: Int
) {
    val mutedColor = AppColors.TextPrimary.copy(alpha = 0.55f)
    val mutedStyle = AppTextStyles.BodySmall.copy(fontSize = 13.sp)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = AppColors.Rating,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))
        AppText(
            text = formatRating(rating),
            style = AppTextStyles.Heading.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.width(4.dp))
        AppText(text = "/ 10", style = mutedStyle, color = mutedColor)
        if (voteCount > 0) {
            Spacer(Modifier.width(8.dp))
            AppText(
                text = "(${formatVoteCount(voteCount)})",
                style = mutedStyle,
                color = mutedColor
            )
        }
    }
}
