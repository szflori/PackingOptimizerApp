package com.example.packingoptimizerapp.android.ui.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun MainLayout(
    content: @Composable (PaddingValues) -> Unit
){
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Drawer(drawerState = drawerState, navController = navController) {
        Scaffold(topBar = { TopBar(title = "Optimalizálás", onMenuClick = {
            scope.launch {
                if (drawerState.isClosed) {
                    drawerState.open()
                } else {
                    drawerState.close()
                }
            }
        }) }) {innerPadding -> content(innerPadding)  }
    }
}