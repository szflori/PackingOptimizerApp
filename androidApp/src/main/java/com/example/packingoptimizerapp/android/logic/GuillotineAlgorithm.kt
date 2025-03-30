package com.example.packingoptimizerapp.android.logic

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

data class Sheet(val width: Int, val height: Int)

@Parcelize
data class Piece(val width: Int, val height: Int, val colorLong: ULong): Parcelable {
    val color: Color get() = Color(colorLong)
}

@Parcelize
data class PlacedPiece(val piece: Piece, val x: Int, val y: Int, val rotated: Boolean) : Parcelable

fun guillotineCut(sheet: Sheet, pieces: MutableList<Piece>): List<PlacedPiece> {
    val placedPieces = mutableListOf<PlacedPiece>()

    fun cut(area: Sheet, xOffset: Int, yOffset: Int): Boolean {
        if (pieces.isEmpty()) return false

        // Mohó kiválasztás: legnagyobb darab ami elfér
        val pieceIndex = pieces.indexOfFirst { (it.width <= area.width && it.height <= area.height) || (it.height <= area.width && it.width <= area.height) }

        if (pieceIndex == -1) return false // nem fér több darab

        val piece = pieces.removeAt(pieceIndex)

        val rotated = !(piece.width <= area.width && piece.height <= area.height)
        val placedWidth = if (rotated) piece.height else piece.width
        val placedHeight = if (rotated) piece.width else piece.height

        placedPieces.add(PlacedPiece(piece, xOffset, yOffset, rotated))

        // Jobb oldali maradék
        val right = Sheet(area.width - placedWidth, placedHeight)
        if (right.width > 0 && right.height > 0) cut(right, xOffset + placedWidth, yOffset)

        // Alsó maradék
        val bottom = Sheet(area.width, area.height - placedHeight)
        if (bottom.width > 0 && bottom.height > 0) cut(bottom, xOffset, yOffset + placedHeight)

        return true
    }

    cut(sheet, 0, 0)
    return placedPieces
}
