package com.ngapps.material3adaptivenavigationsuitedemo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.ngapps.material3adaptivenavigationsuitedemo.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val icon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        icon = Icons.Outlined.Home,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    LIST(
        icon = Icons.Outlined.List,
        iconTextId = R.string.list,
        titleTextId = R.string.list,
    ),
    FAVORITE(
        icon = Icons.Outlined.Favorite,
        iconTextId = R.string.favorite,
        titleTextId = R.string.favorite,
    )
}
