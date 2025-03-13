package com.example.packingoptimizerapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.packingoptimizerapp.android.ui.layouts.Drawer
import com.example.packingoptimizerapp.android.ui.layouts.MainLayout
import com.example.packingoptimizerapp.android.ui.layouts.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout { innerPadding ->
                Text(
                    "Click the back button to pop from the back stack.",
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
