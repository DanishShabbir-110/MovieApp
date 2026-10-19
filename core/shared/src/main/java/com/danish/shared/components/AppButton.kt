package com.danish.shared.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    containerColor: Color = AppColors.Primary,
    contentColor: Color = AppColors.OnPrimary,
    shape: Shape = AppShapes.Button,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    Button(
        onClick=onClick,
        modifier=modifier,
        enabled = enabled && !isLoading,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ){
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
        }else{
            if(icon!=null){
                Icon(imageVector = icon,contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
            }
            AppText(
                text = text,
                color=contentColor,
                style = AppTextStyles.Button
            )
        }
    }
}