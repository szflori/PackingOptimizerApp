package com.example.packingoptimizerapp.android.logic

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class ColoredItem(
    val width: Int,
    val colorLong: ULong
) : Parcelable {
    val color: Color get() = Color(colorLong)
}

@Parcelize
data class CuttingResult(val bins: List<List<ColoredItem>>, val waste: List<Int>) : Parcelable
// TODO adjuk át a színt
fun firstFitDecreasing(sheetWidth: Int, items: List<ColoredItem>): CuttingResult {
    val sortedItems = items.sortedByDescending  {it.width}
    val bins = mutableListOf<MutableList<ColoredItem>>()

    for (item in sortedItems) {
        var placed = false

        for (bin in bins) {
            if (bin.sumOf { it.width } + item.width <= sheetWidth) {
                bin.add(item)
                placed = true
                break
            }
        }

        if (!placed) {
            bins.add(mutableListOf(item))
        }
    }

    val waste = bins.map { sheetWidth - it.sumOf { item -> item.width } }
    return CuttingResult(bins, waste)
}
