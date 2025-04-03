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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.packingoptimizerapp.android.logic.Piece
import com.example.packingoptimizerapp.android.logic.Sheet
import com.example.packingoptimizerapp.android.logic.guillotineCut
import com.example.packingoptimizerapp.android.ui.components.ColorPickerButton
import com.example.packingoptimizerapp.android.ui.components.CustomNumberField

data class LapItem(
    var width: String,
    var height: String,
    var quantity: String,
    var color: Color
)

@Composable
fun Cutting2DScreen(navController: NavController) {
    var sheetWidth by remember { mutableStateOf("200") }
    var sheetHeight by remember { mutableStateOf("200") }
    var lapList = remember { mutableStateListOf<LapItem>() }

    val isOptimizationEnabled by remember { derivedStateOf {
        // Készlet szélessége helyes szám (> 0)
        val widthValid = sheetWidth.toIntOrNull()?.let { it > 0 } == true
        val heightValid = sheetHeight.toIntOrNull()?.let { it > 0 } == true

        // Legalább két lap, és minden lap érvényes számokat tartalmaz
        val validLapsCount = lapList.count { lap ->
            val width = lap.width.toIntOrNull()
            val height = lap.height.toIntOrNull()
            val quantity = lap.quantity.toIntOrNull()

            width != null && width > 0 && height != null && height > 0 &&  quantity != null && quantity > 0
        }

        widthValid && heightValid && validLapsCount >= 2
    } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("2D Vágás", style = MaterialTheme.typography.headlineSmall)

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

            CustomNumberField(
                value = sheetHeight,
                label = "Hosszúság (cm)",
                onValueChange = {sheetHeight = it},
                onClearClick = { sheetHeight = "" }
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
                itemsIndexed(lapList) { index, lap ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ColorPickerButton(
                            selectedColor = lap.color,
                            onColorSelected = { newColor ->
                                    lapList[index] = lapList[index].copy(color = newColor)
                            }
                        )

                        OutlinedTextField(
                            value = lap.width,
                            label = { Text("Szélesség (cm)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = { newValue ->
                                    lapList[index] = lapList[index].copy(width = newValue)
                            },
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = lap.height,
                            label = { Text("Hosszúság (cm)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = { newValue ->
                                    lapList[index] = lapList[index].copy(height = newValue)
                            },
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = lap.quantity,
                            label = { Text("Mennyiség") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = { newValue ->
                                    lapList[index] = lapList[index].copy(quantity = newValue)
                            },
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = {
                            lapList.removeAt(index)
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Törlés")
                        }
                    }
                }
            }

            Button(
                onClick = {  lapList.add(LapItem("", "", "", Color.Gray)) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Hozzáadás")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lap hozzáadása")
            }

            Button(
                onClick = {
                    val widthInt = sheetWidth.toIntOrNull() ?: return@Button
                    val heightInt = sheetHeight.toIntOrNull() ?: return@Button

                    val itemList = lapList.flatMap { lap ->
                        val width  = lap.width.toIntOrNull()
                        val height  = lap.height.toIntOrNull()
                        val quantity  = lap.quantity.toIntOrNull()
                        val colorLong = lap.color.value // <- Így konvertálunk Long-á
                        if (width != null && height != null && quantity != null) {
                            List(quantity) { Piece(width, height, quantity, colorLong) }
                        } else emptyList()
                    }.toMutableList()

                    val result = guillotineCut(Sheet(widthInt, heightInt), itemList)

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "cutting_result_2d",
                        value = result
                    )

                    navController.navigate("cutting_result_2d")
                },
                enabled = isOptimizationEnabled,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Optimalizálás")
            }
        }
    }
}