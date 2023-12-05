package com.ngapps.material3adaptivenavigationsuitedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.collectWindowSizeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.core.view.WindowCompat
import com.ngapps.material3adaptivenavigationsuitedemo.ui.MainApp
import com.ngapps.material3adaptivenavigationsuitedemo.ui.theme.MainTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainTheme {
                val windowSize by collectWindowSizeAsState()
                MainApp(
                    windowSize = windowSize.toDpSize(),
                )
            }
        }
    }
}

@Composable
private fun IntSize.toDpSize(): DpSize = with(LocalDensity.current) {
    DpSize(width.toDp(), height.toDp())
}