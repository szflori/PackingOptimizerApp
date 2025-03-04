package com.example.packingoptimizerapp.android.logic

data class Item(val width: Int, val height: Int, val color: Long)

data class Bin(
    val width: Int,
    val height: Int,
    val items: MutableList<ItemPlacement> = mutableListOf()
)

data class ItemPlacement(val item: Item, val x: Int, val y: Int)

fun packItems(container: Bin, items: List<Item>): List<ItemPlacement> {
    val sortedItems = items.sortedByDescending { it.height } // Magasság szerint csökkenő sorrend
    val placements = mutableListOf<ItemPlacement>()

    var currentX = 0
    var currentY = 0
    var rowHeight = 0

    for (item in sortedItems) {
        if (currentX + item.width > container.width) {
            // Új sor kezdése
            currentX = 0
            currentY += rowHeight
            rowHeight = 0
        }

        if (currentY + item.height <= container.height) {
            placements.add(ItemPlacement(item, currentX, currentY))
            currentX += item.width
            rowHeight = maxOf(rowHeight, item.height)
        }
    }
    return placements
}