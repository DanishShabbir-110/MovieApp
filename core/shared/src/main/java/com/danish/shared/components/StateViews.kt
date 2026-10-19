package com.danish.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = AppColors.Primary
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = color, modifier = Modifier.size(size))
    }
}

@Composable
fun CenteredMessage(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AppText(text = text, style = AppTextStyles.BodyCompact, color = AppColors.TextSecondary)
    }
}

@Composable
fun StateView(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTint: Color = AppColors.TextSecondary,
    iconSize: Dp = 64.dp,
    message: String? = null,
    messageAlign: TextAlign = TextAlign.Center,
    action: (@Composable () -> Unit)? = null
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(iconSize)
                )
                Spacer(Modifier.height(16.dp))
            }

            AppText(
                text = title,
                style = AppTextStyles.Title,
                textAlign = TextAlign.Center
            )

            if (message != null) {
                Spacer(Modifier.height(8.dp))
                AppText(
                    text = message,
                    style = AppTextStyles.BodySmall.copy(lineHeight = 20.sp),
                    color = AppColors.TextSecondary,
                    textAlign = messageAlign
                )
            }

            if (action != null) {
                Spacer(Modifier.height(24.dp))
                action()
            }
        }
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Something went wrong"
) {
    StateView(
        modifier = modifier,
        title = title,
        message = message,
        icon = Icons.Outlined.WarningAmber,
        iconTint = AppColors.Primary,
        iconSize = 52.dp,
        action = {
            AppButton(
                text = "Retry",
                icon = Icons.Outlined.Refresh,
                onClick = onRetry
            )
        }
    )
}