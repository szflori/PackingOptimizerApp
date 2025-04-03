package com.example.packingoptimizerapp.android.logic

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sheet(val width: Int, val height: Int):Parcelable

@Parcelize
data class Piece(val width: Int, val height: Int,  val quantity: Int, val colorLong: ULong): Parcelable {
    val color: Color get() = Color(colorLong)
}

@Parcelize
data class PlacedPiece(val piece: Piece, val x: Int, val y: Int, val rotated: Boolean) : Parcelable

@Parcelize
data class SheetResult(
    val sheetNumber: Int,
    val sheet: Sheet,
    val placedPieces: List<PlacedPiece>
) : Parcelable

data class SheetArea(val sheet: Sheet, val x: Int, val y: Int)

@Parcelize
data class Cutting2DResult(
    val sheets: List<SheetResult>
) : Parcelable

fun guillotineCut(sheet: Sheet, pieces: MutableList<Piece>): Cutting2DResult {
    val expandedPieces = pieces.flatMap { piece -> List(piece.quantity) { piece.copy(quantity = 1) } }.toMutableList()
    val sheetsResults = mutableListOf<SheetResult>()
    var sheetCount = 0

    while (expandedPieces.isNotEmpty()){
        val placedPieces = mutableListOf<PlacedPiece>()
        val availableArea = mutableListOf(SheetArea(sheet, 0, 0))

        fun cut(): Boolean {
            if (expandedPieces.isEmpty()) return false

            for ((index, area) in availableArea.withIndex()) {
                val pieceIndex = expandedPieces.indexOfFirst {
                    (it.width <= area.sheet.width && it.height <= area.sheet.height) ||
                            (it.height <= area.sheet.width && it.width <= area.sheet.height)
                }

                if (pieceIndex != -1) {
                    val piece = expandedPieces.removeAt(pieceIndex)

                    val rotated = !(piece.width <= area.sheet.width && piece.height <= area.sheet.height)
                    val placedWidth = if (rotated) piece.height else piece.width
                    val placedHeight = if (rotated) piece.width else piece.height

                    placedPieces.add(PlacedPiece(piece, area.x, area.y, rotated))

                    val right = Sheet(area.sheet.width - placedWidth, placedHeight)
                    if (right.width > 0 && right.height > 0) availableArea.add(SheetArea(right, area.x + placedWidth, area.y))

                    val bottom = Sheet(area.sheet.width, area.sheet.height - placedHeight)
                    if (bottom.width > 0 && bottom.height > 0) availableArea.add(SheetArea(bottom, area.x, area.y + placedHeight))

                    availableArea.removeAt(index)
                    return true
                }
            }

            return false
        }

        while (cut()) {}

        sheetCount++
        sheetsResults.add(SheetResult(sheetCount, sheet, placedPieces))
    }

    return Cutting2DResult(sheetsResults)
}
