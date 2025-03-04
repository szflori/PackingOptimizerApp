package com.example.packingoptimizerapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.packingoptimizerapp.android.ui.Container2DInputScreen
import com.example.packingoptimizerapp.android.ui.Container3DInputScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Container2DInputScreen()
            }
        }
    }
}

@Preview
@Composable
fun PreviewContainerInputScreen() {
    MyApplicationTheme {
        MainActivity()
    }
}