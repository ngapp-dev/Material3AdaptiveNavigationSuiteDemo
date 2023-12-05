package com.ngapps.material3adaptivenavigationsuitedemo.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ngapps.material3adaptivenavigationsuitedemo.list.ListRoute

const val listNavigationRoute = "list_route"

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    this.navigate(listNavigationRoute, navOptions)
}

fun NavGraphBuilder.listScreen() {
    composable(
        route = listNavigationRoute,
    ) {
        ListRoute()
    }
}
