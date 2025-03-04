package com.example.packingoptimizerapp.android.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import com.example.packingoptimizerapp.android.logic.Bin
import com.example.packingoptimizerapp.android.logic.Item
import com.example.packingoptimizerapp.android.logic.packItems

@Composable
fun PackingVisualizer(containerWidth: Int, containerHeight: Int, items: List<Item>) {
    val container = Bin(containerWidth, containerHeight)
    val packedItems = remember { packItems(container, items) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Rajzoljuk a konténer hátterét
        drawRect(
            color = Color.Gray,
            size = Size(containerWidth.toFloat(), containerHeight.toFloat())
        )

        // Kirajzoljuk a tárgyakat
        for (placement in packedItems) {
            drawRect(
                color = Color(placement.item.color),
                topLeft = Offset(placement.x.toFloat(), placement.y.toFloat()),
                size = Size(placement.item.width.toFloat(), placement.item.height.toFloat())
            )
        }
    }
}