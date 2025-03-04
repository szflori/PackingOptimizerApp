package com.example.packingoptimizerapp.android.ui;

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Container3DInputScreen() {
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var depth by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 64.dp, horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add meg a konténer méreteit (cm)",
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = width,
                onValueChange = { width = it },
                label = { Text("Szélesség (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Magasság (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = depth,
                onValueChange = { depth = it },
                label = { Text("Mélység (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = {
                val w = width.toIntOrNull() ?: 0
                val h = height.toIntOrNull() ?: 0
                val d = depth.toIntOrNull() ?: 0
                println("Megadott méretek: Szélesség: $w cm, Magasság: $h cm, Mélység: $d cm")
            }) {
                Text("Mentés")
            }
        }
    }
}

@Preview
@Composable
fun PreviewContainerInputScreen() {
    Container3DInputScreen()
}
