package com.danish.main_impl.presentation.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    HOME(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),
    SEARCH(
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Outlined.Search
    )
}


@Composable
fun BottomNavBar(
    selectedItem: BottomNavItem,
    onItemClick: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(width = 1.dp, color = CineHubRed.copy(alpha = 0.35f)),
            color = BarBackground
        ) {
            Row(
                modifier = Modifier.padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem.entries.forEach { item ->
                    BottomBarItem(
                        item = item,
                        selected = item == selectedItem,
                        onClick = {
                            onItemClick(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val itemWidth by animateDpAsState(
        targetValue = if (selected) 118.dp else 52.dp,
        label = "bottomNavItemWidth"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) {
            CineHubRed
        } else {
            Color.Transparent
        },
        label = "bottomNavBackground"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected) {
            Color.White
        } else {
            UnselectedIcon
        },
        label = "bottomNavIcon"
    )

    Row(
        modifier = Modifier
            .width(itemWidth)
            .height(52.dp)
            .background(
                color = backgroundColor,
                shape = if (selected) {
                    RoundedCornerShape(26.dp)
                } else {
                    CircleShape
                }
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = if (selected) {
                item.selectedIcon
            } else {
                item.unSelectedIcon
            },
            contentDescription = item.title,
            tint = iconColor,
            modifier = Modifier.size(23.dp)
        )

        AnimatedVisibility(
            visible = selected,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        }
    }
}
private val CineHubRed = Color(0xFFE63946)
private val BarBackground = Color(0xCC111318)
private val UnselectedIcon = Color(0xFF9CA3AD)
