package com.example.packingoptimizerapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.packingoptimizerapp.android.ui.screens.Cutting1DScreen
import com.example.packingoptimizerapp.android.ui.screens.DashboardScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") { DashboardScreen() }
        composable("cutting_1d") { Cutting1DScreen()}
       // composable("packing") { }
    }
}
