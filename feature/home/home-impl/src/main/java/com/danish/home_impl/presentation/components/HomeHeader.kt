package com.danish.home_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.shared.components.AppText
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = AppColors.Primary.copy(alpha = 0.15f),
                        shape = AppShapes.Medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Movie,
                    contentDescription = null,
                    tint = AppColors.Primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val logoStyle = AppTextStyles.Title.copy(fontSize = 22.sp)
                    AppText(text = "Cine", style = logoStyle)
                    AppText(text = "Hub", style = logoStyle, color = AppColors.Primary)
                }
                AppText(
                    text = "Discover Movies",
                    style = AppTextStyles.Caption.copy(fontWeight = FontWeight.Medium),
                    color = AppColors.TextSecondary
                )
            }
        }

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    color = AppColors.Primary,
                    shape = CircleShape
                )
        )
    }
}
