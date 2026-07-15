package com.example.pingport.ui.theme

import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)