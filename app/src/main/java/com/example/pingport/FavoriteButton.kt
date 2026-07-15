package com.example.pingport

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavorite) {
                Icons.Filled.Star
            } else {
                Icons.Outlined.Star
            },
            contentDescription = if (isFavorite) {
                "お気に入りから削除"
            } else {
                "お気に入りに追加"
            },
            tint = if (isFavorite) {
                Color(0xFFFF9800)
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}