package com.example.packingoptimizerapp.android.logic

data class Sheet(val width: Int, val height: Int)  // Az alaplemez mérete
data class CutPiece(val width: Int, val height: Int)  // A kivágandó lemez mérete

// Egy vágási terv: hány darab és milyen vágásokkal oldjuk meg
data class CuttingPlan(val cuts: List<CutPiece>, val cutsCount: Int)

// First Fit Decreasing algoritmus a vágási optimalizálásra
fun optimizeCutting(sheet: Sheet, pieces: List<CutPiece>): CuttingPlan {
    val sortedPieces = pieces.sortedByDescending { it.width } // Csökkenő sorrend

    val cutLines = mutableListOf<Int>() // Vágási helyek nyilvántartása
    val usedWidths = mutableListOf<Int>() // Az eddig felhasznált szélességek

    for (piece in sortedPieces) {
        var placed = false

        // Megpróbáljuk egy már létező vágásba illeszteni
        for (i in usedWidths.indices) {
            if (usedWidths[i] + piece.width <= sheet.width) {
                usedWidths[i] += piece.width
                placed = true
                break
            }
        }

        // Ha nem illeszthető be egy meglévő sorba, új vágást hozunk létre
        if (!placed) {
            usedWidths.add(piece.width)
            cutLines.add(piece.width)
        }
    }

    return CuttingPlan(pieces, cutLines.size)
}

fun randomColor(): Long {
    return (0xFF000000..0xFFFFFFFF).random()
}