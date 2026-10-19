package com.danish.home_impl.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.danish.shared.components.AppText
import com.danish.shared.components.RatingBadge
import com.danish.shared.components.RatingBadgeSize
import com.danish.shared.components.TagChip
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles

@SuppressLint("DefaultLocale")
@Composable
fun HeroMovieCard(
    title: String,
    overview: String,
    backdropUrl: String?,
    rating: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(AppShapes.ExtraLarge)
            .border(
                border = BorderStroke(1.dp, AppColors.Border),
                shape = AppShapes.ExtraLarge
            )
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = backdropUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.15f to Color.Transparent,
                        0.55f to AppColors.Background.copy(alpha = 0.4f),
                        1.00f to AppColors.Background.copy(alpha = 0.98f)
                    )
                )
        )
        TagChip(
            text = "FEATURED",
            containerColor = AppColors.Primary,
            contentColor = AppColors.OnPrimary,
            style = AppTextStyles.Label.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            shape = RoundedCornerShape(8.dp),
            padding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            AppText(
                text = title,
                style = AppTextStyles.Title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(6.dp))

            RatingBadge(
                rating = rating,
                size = RatingBadgeSize.Medium,
                background = AppColors.Scrim
            )

            if (overview.isNotBlank()) {
                Spacer(Modifier.height(6.dp))

                AppText(
                    text = overview,
                    style = AppTextStyles.Label.copy(
                        fontWeight = FontWeight.Normal,
                        lineHeight = 15.sp
                    ),
                    color = Color.White.copy(alpha = 0.80f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
