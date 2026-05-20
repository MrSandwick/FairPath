package com.example.fairpath.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fairpath.screens.ContactsScreen
import com.example.fairpath.screens.ExportScreen
import com.example.fairpath.screens.FollowUpScreen
import com.example.fairpath.screens.HomeScreen
import com.example.fairpath.screens.ScanScreen

sealed class Screen(val route: String) {
    object Home     : Screen("home")
    object Scan     : Screen("scan")
    object Contacts : Screen("contacts")
    object Export   : Screen("export")
    object FollowUp : Screen("follow_up")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController   = navController,
                onScanClick     = { navController.navigate(Screen.Scan.route) },
                onContactsClick = { navController.navigate(Screen.Contacts.route) },
                onExportClick   = { navController.navigate(Screen.Export.route) },
                onFollowUpClick = { navController.navigate(Screen.FollowUp.route) }
            )
        }
        composable(Screen.Scan.route)     { ScanScreen(navController) }
        composable(Screen.Contacts.route) { ContactsScreen(navController) }
        composable(Screen.Export.route)   { ExportScreen(navController) }
        composable(Screen.FollowUp.route) { FollowUpScreen(navController) }
    }
}
