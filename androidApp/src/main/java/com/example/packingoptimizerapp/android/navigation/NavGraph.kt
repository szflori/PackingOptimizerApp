package com.example.packingoptimizerapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.packingoptimizerapp.android.logic.Cutting2DResult
import com.example.packingoptimizerapp.android.logic.CuttingResult
import com.example.packingoptimizerapp.android.ui.screens.Cutting1DScreen
import com.example.packingoptimizerapp.android.ui.screens.Cutting2DResultScreen
import com.example.packingoptimizerapp.android.ui.screens.Cutting2DScreen
import com.example.packingoptimizerapp.android.ui.screens.CuttingResultScreen
import com.example.packingoptimizerapp.android.ui.screens.DashboardScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") { DashboardScreen() }
        composable("cutting_1d") { Cutting1DScreen(navController)}
        composable("cutting_result") { backStackEntry ->
            val result = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<CuttingResult>("cutting_result")

            result?.let { CuttingResultScreen(it, navController) }
        }
        composable("cutting_2d") { Cutting2DScreen(navController)  }
        composable("cutting_result_2d") { backStackEntry ->
            val  result = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Cutting2DResult>("cutting_result_2d")

            result?.let { Cutting2DResultScreen(it, navController) }
        }
    }
}
