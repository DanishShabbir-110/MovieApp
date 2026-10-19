package com.danish.shared.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle= AppTextStyles.Body,
    color: Color = AppColors.TextPrimary,
    maxLines:Int=Int.MAX_VALUE,
    overflow: TextOverflow= TextOverflow.Clip,
    textAlign: TextAlign? = null
) {
   Text(
       text = text,
       modifier = modifier,
       style = style,
       color = color,
       maxLines = maxLines,
       overflow = overflow,
       textAlign = textAlign
   )
}