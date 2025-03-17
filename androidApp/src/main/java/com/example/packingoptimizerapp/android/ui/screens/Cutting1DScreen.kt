package com.example.packingoptimizerapp.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.packingoptimizerapp.android.ui.components.ColorPickerButton
import com.example.packingoptimizerapp.android.ui.components.CustomNumberField

@Composable
fun Cutting1DScreen() {
    var sheetWidth by remember { mutableStateOf("200") }
    var lapList by remember { mutableStateOf(mutableListOf<Triple<String, String, Color>>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text("1D Vágás", style = MaterialTheme.typography.headlineSmall)

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Készlet", style = MaterialTheme.typography.titleMedium)

            CustomNumberField(
                value = sheetWidth,
                label = "Szélessége (cm)",
                onValueChange = {sheetWidth = it},
                onClearClick = { sheetWidth = "" }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lapok", style = MaterialTheme.typography.titleMedium)

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                itemsIndexed(lapList) { index, (width, quantity, color) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ColorPickerButton(
                            selectedColor = color,
                            onColorSelected = { newColor ->
                                lapList = lapList.toMutableList().apply { this[index] = Triple(width, quantity, newColor) }
                            }
                        )

                        OutlinedTextField(
                            value = width,
                            label = { Text("Szélesség (cm)") },
                            onValueChange = { newValue ->
                                lapList = lapList.toMutableList().apply { this[index] = Triple(newValue, quantity, color) }
                            },
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = quantity,
                            label = { Text("Mennyiség") },
                            onValueChange = { newValue ->
                                lapList = lapList.toMutableList().apply { this[index] = Triple(width, newValue, color) }
                            },
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = {
                            lapList = lapList.toMutableList()
                                .apply { removeAt(index) } // Lap eltávolítása
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Törlés")
                        }
                    }
                }
            }

            Button(
                onClick = { lapList = lapList.toMutableList().apply { add(Triple("", "", Color.Gray)) } },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Hozzáadás")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lap hozzáadása")
            }
        }
    }
}
