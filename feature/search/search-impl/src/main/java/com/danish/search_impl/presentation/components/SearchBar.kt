package com.danish.search_impl.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danish.search_impl.presentation.screens.search.CineHubRed
import com.danish.search_impl.presentation.screens.search.SearchFieldBackground
import com.danish.search_impl.presentation.screens.search.SecondaryText

@Composable
fun SearchBar(
    query: String, onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = "Search movies...", color = SecondaryText
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search, contentDescription = null, tint = SecondaryText
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = CineHubRed,
            unfocusedBorderColor = Color(0xFF252C35),
            focusedContainerColor = SearchFieldBackground,
            unfocusedContainerColor = SearchFieldBackground,
            cursorColor = CineHubRed
        )
    )
}