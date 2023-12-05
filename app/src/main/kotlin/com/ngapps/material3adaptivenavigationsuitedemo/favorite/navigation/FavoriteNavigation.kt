package com.ngapps.material3adaptivenavigationsuitedemo.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ngapps.material3adaptivenavigationsuitedemo.favorite.FavoriteRoute

const val favoriteNavigationRoute = "favorite_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) {
    this.navigate(favoriteNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoriteScreen() {
    composable(
        route = favoriteNavigationRoute,
    ) {
        FavoriteRoute()
    }
}
