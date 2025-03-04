package com.example.packingoptimizerapp.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import com.example.packingoptimizerapp.android.logic.Item
import kotlin.random.Random

@Composable
fun Container2DInputScreen() {
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var itemCount by remember { mutableStateOf("10") } // Új mező: tárgyak száma
    var isPacked by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf(generateRandomItems(itemCount.toIntOrNull() ?: 10)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add meg a konténer méreteit", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = width,
            onValueChange = { width = it },
            label = { Text("Szélesség (px)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Magasság (px)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = itemCount,
            onValueChange = { itemCount = it },
            label = { Text("Tárgyak száma") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                isPacked = true
                items = generateRandomItems(itemCount.toIntOrNull() ?: 10) // Frissítjük a tárgyakat
            }) {
                Text("Mutasd a Pakolást")
            }

            Button(
                onClick = {
                    // Az adatok alaphelyzetbe állítása
                    width = ""
                    height = ""
                    isPacked = false
                    items = generateRandomItems(itemCount.toIntOrNull() ?: 10) // Friss tárgyak generálása
                },
            ) {
                Text("Alaphelyzet")
            }
        }

        if (isPacked) {
            val w = width.toIntOrNull() ?: 0
            val h = height.toIntOrNull() ?: 0
            PackingVisualizer(w, h, items)
        }
    }
}

// Generáljunk véletlenszerű tárgyakat
fun generateRandomItems(count: Int): List<Item> {
    return List(count) {
        Item(
            width = Random.nextInt(20, 80),
            height = Random.nextInt(20, 80),
            color = Random.nextLong(0xFF000000, 0xFFFFFFFF) // Véletlenszerű szín
        )
    }
}