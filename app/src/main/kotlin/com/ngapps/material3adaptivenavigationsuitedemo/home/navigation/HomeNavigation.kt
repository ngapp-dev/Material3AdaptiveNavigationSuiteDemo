package com.ngapps.material3adaptivenavigationsuitedemo.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ngapps.material3adaptivenavigationsuitedemo.home.HomeRoute

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeRoute()
    }
}
