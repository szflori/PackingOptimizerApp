package com.example.packingoptimizerapp.android.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.packingoptimizerapp.android.logic.PlacedPiece
import kotlin.math.min

@Composable
fun Cutting2DResultScreen(result: List<PlacedPiece>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Vágási terv:", style = MaterialTheme.typography.headlineSmall)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF8F8F8))
        ){


        Canvas(modifier = Modifier.size(1000.dp)) {
            val maxWidth = result.maxOfOrNull { it.x + if (it.rotated) it.piece.height else it.piece.width } ?: 1
            val maxHeight = result.maxOfOrNull { it.y + if (it.rotated) it.piece.width else it.piece.height } ?: 1

            val scaleX = size.width / maxWidth
            val scaleY = size.height / maxHeight
            val scale = min(scaleX, scaleY)

            result.forEach { placedPiece ->
                val pieceWidth = (if (placedPiece.rotated) placedPiece.piece.height else placedPiece.piece.width) * scale
                val pieceHeight = (if (placedPiece.rotated) placedPiece.piece.width else placedPiece.piece.height) * scale

                drawRect(
                    color = placedPiece.piece.color,
                    topLeft = Offset(placedPiece.x * scale, placedPiece.y * scale),
                    size = Size(pieceWidth, pieceHeight),
                    style = Stroke(width = 2.dp.toPx())
                )

                drawContext.canvas.nativeCanvas.drawText(
                    "${placedPiece.piece.width}x${placedPiece.piece.height}",
                    (placedPiece.x * scale + 8.dp.toPx()),
                    (placedPiece.y * scale + 20.dp.toPx()),
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 12.sp.toPx()
                    }
                )
            }
            drawRect(
                color = Color.Black,
                size = Size(maxWidth * scale, maxHeight * scale),
                style = Stroke(width = 4.dp.toPx())
            )
        }
        }
    }
}