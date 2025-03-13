package com.example.packingoptimizerapp.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class OptimizationType {
    PACKING, CUTTING
}

@Composable
fun ConfigurationScreen(onSave: (OptimizationType) -> Unit) {
    var selectedOption by remember { mutableStateOf(OptimizationType.PACKING) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Válaszd ki az optimalizáció típusát:", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            RadioButton(
                selected = selectedOption == OptimizationType.PACKING,
                onClick = { selectedOption = OptimizationType.PACKING }
            )
            Text("2D Pakolás")
        }

        Row {
            RadioButton(
                selected = selectedOption == OptimizationType.CUTTING,
                onClick = { selectedOption = OptimizationType.CUTTING }
            )
            Text("Vágás Optimalizálás")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSave(selectedOption) }) {
            Text("Mentés és folytatás")
        }
    }
}
