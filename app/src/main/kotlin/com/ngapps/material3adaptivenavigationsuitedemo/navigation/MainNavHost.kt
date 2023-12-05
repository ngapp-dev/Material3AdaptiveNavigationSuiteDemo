package com.ngapps.material3adaptivenavigationsuitedemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.ngapps.material3adaptivenavigationsuitedemo.favorite.navigation.favoriteScreen
import com.ngapps.material3adaptivenavigationsuitedemo.home.navigation.homeNavigationRoute
import com.ngapps.material3adaptivenavigationsuitedemo.home.navigation.homeScreen
import com.ngapps.material3adaptivenavigationsuitedemo.list.navigation.listScreen
import com.ngapps.material3adaptivenavigationsuitedemo.ui.MainAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun MainNavHost(
    appState: MainAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        homeScreen()
        listScreen()
        favoriteScreen()
    }
}