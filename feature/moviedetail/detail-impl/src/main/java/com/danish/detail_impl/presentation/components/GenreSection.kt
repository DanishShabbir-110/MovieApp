package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.shared.components.PillChip
import com.danish.shared.components.SectionTitle

@Composable
fun GenreSection(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    if (genres.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SectionTitle(title = "Genres")
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                PillChip(text = genre)
            }
        }
    }
}