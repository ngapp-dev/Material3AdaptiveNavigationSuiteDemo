package com.ngapps.material3adaptivenavigationsuitedemo.ui

import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ngapps.material3adaptivenavigationsuitedemo.favorite.navigation.favoriteNavigationRoute
import com.ngapps.material3adaptivenavigationsuitedemo.favorite.navigation.navigateToFavorite
import com.ngapps.material3adaptivenavigationsuitedemo.home.navigation.homeNavigationRoute
import com.ngapps.material3adaptivenavigationsuitedemo.home.navigation.navigateToHome
import com.ngapps.material3adaptivenavigationsuitedemo.list.navigation.listNavigationRoute
import com.ngapps.material3adaptivenavigationsuitedemo.list.navigation.navigateToList
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.TopLevelDestination
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.TopLevelDestination.FAVORITE
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.TopLevelDestination.HOME
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.TopLevelDestination.LIST

@Composable
fun rememberMainAppState(
    windowSize: DpSize,
    navController: NavHostController = rememberNavController(),
): MainAppState {

    return remember(navController, windowSize) {
        MainAppState(navController, windowSize)
    }
}

@Stable
class MainAppState(
    val navController: NavHostController,
    private val windowSize: DpSize,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> HOME
            listNavigationRoute -> LIST
            favoriteNavigationRoute -> FAVORITE
            else -> null
        }

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * Per <a href="https://m3.material.io/components/navigation-drawer/guidelines">Material Design 3 guidelines</a>,
     * the selection of the appropriate navigation component should be contingent on the available
     * window size:
     * - Bottom Bar for compact window sizes (below 600dp)
     * - Navigation Rail for medium and expanded window sizes up to 1240dp (between 600dp and 1240dp)
     * - Navigation Drawer to window size above 1240dp
     */
    @OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
    val navigationSuiteType: NavigationSuiteType
        @Composable get() {
            return if (windowSize.width > 1240.dp) {
                NavigationSuiteType.NavigationDrawer
            } else if (windowSize.width >= 600.dp) {
                NavigationSuiteType.NavigationRail
            } else {
                NavigationSuiteType.NavigationBar
            }
        }

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param destination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        when (destination) {
            HOME -> navController.navigateToHome(topLevelNavOptions)
            LIST -> navController.navigateToList(topLevelNavOptions)
            FAVORITE -> navController.navigateToFavorite(topLevelNavOptions)
        }
    }
}