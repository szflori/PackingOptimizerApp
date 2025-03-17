package com.example.packingoptimizerapp.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import com.example.packingoptimizerapp.android.logic.*

@Composable
fun ContainerInputScreen() {
    var sheetWidth by remember { mutableStateOf("200") }
    var sheetHeight by remember { mutableStateOf("100") }
    var isOptimized by remember { mutableStateOf(false) }
    val pieces = listOf(
        CutPiece(50, 100),
        CutPiece(75, 100),
        CutPiece(30, 100)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Adja meg az alaplemez méreteit", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = sheetWidth,
            onValueChange = { sheetWidth = it },
            label = { Text("Lemez szélessége (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = sheetHeight,
            onValueChange = { sheetHeight = it },
            label = { Text("Lemez magassága (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = { isOptimized = true }) {
            Text("Optimalizálás")
        }

        if (isOptimized) {
            val width = sheetWidth.toIntOrNull() ?: 0
            val height = sheetHeight.toIntOrNull() ?: 0
        }
    }
}
