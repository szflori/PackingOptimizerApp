package com.example.packingoptimizerapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.packingoptimizerapp.android.navigation.AppNavGraph
import com.example.packingoptimizerapp.android.ui.layouts.Drawer
import com.example.packingoptimizerapp.android.ui.layouts.MainLayout
import com.example.packingoptimizerapp.android.ui.layouts.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MainLayout(navController) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    AppNavGraph(navController)
                }
            }
        }
    }
}
