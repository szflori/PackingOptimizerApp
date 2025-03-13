package com.example.packingoptimizerapp.android.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.packingoptimizerapp.android.logic.*

@Composable
fun CuttingVisualizer(sheet: Sheet, pieces: List<CutPiece>) {
    val cuttingPlan = remember { optimizeCutting(sheet, pieces) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Minimális vágásszám: ${cuttingPlan.cutsCount}", style = MaterialTheme.typography.headlineMedium)

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            var currentX = 0f

            for (cut in cuttingPlan.cuts) {
                drawRect(
                    color = Color.Blue,
                    topLeft = androidx.compose.ui.geometry.Offset(currentX, 0f),
                    size = androidx.compose.ui.geometry.Size(cut.width.toFloat(), sheet.height.toFloat())
                )
                currentX += cut.width
            }
        }
    }
}
