package com.ngapps.material3adaptivenavigationsuitedemo.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.MainNavHost
import com.ngapps.material3adaptivenavigationsuitedemo.navigation.TopLevelDestination
import com.ngapps.material3adaptivenavigationsuitedemo.ui.component.MainTopAppBar

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
)
@Composable
fun MainApp(
    windowSize: DpSize,
    appState: MainAppState = rememberMainAppState(
        windowSize = windowSize,
    ),
) {

    val currentDestination = appState.currentDestination
    val topLevelDestination = appState.currentTopLevelDestination
    NavigationSuiteScaffold(
        layoutType = appState.navigationSuiteType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(),
        navigationSuiteItems = {
            if (topLevelDestination != null) {
                appState.topLevelDestinations.forEach { destination ->
                    val isSelected =
                        currentDestination.isTopLevelDestinationInHierarchy(destination)
                    item(
                        selected = isSelected,
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) },
                        onClick = { appState.navigateToTopLevelDestination(destination) },
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    MainTopAppBar(
                        titleRes = destination.titleTextId,
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { padding ->
            MainNavHost(
                appState = appState,
                modifier = Modifier.padding(padding),
            )
        }

        // TODO: We may want to add padding or spacer when the snackbar is shown so that
        //  content doesn't display behind it.
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false