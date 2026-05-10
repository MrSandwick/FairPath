package com.example.fairpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fairpath.navigation.AppNavigation
import com.example.fairpath.ui.theme.FairPathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FairPathTheme {
                AppNavigation()
            }
        }
    }
}
