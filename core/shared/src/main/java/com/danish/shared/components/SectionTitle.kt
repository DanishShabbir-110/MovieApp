package com.danish.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles

@Composable
fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(18.dp)
                .background(color = AppColors.Primary, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        AppText(text=title,style= AppTextStyles.Heading)
    }
}