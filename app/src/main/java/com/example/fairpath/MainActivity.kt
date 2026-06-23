package com.example.fairpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import com.example.fairpath.navigation.AppNavigation
import com.example.fairpath.ui.LocalWindowSizeClass
import com.example.fairpath.ui.theme.FairPathTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            FairPathTheme {
                CompositionLocalProvider(LocalWindowSizeClass provides windowSizeClass) {
                    AppNavigation()
                }
            }
        }
    }
}