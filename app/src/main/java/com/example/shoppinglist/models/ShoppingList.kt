package com.example.shoppinglist.models

data class ShoppingList(
    val id: Int,
    var name: String,
    val items: MutableList<ShoppingListItem> = mutableListOf()
) {
    val progress = Pair(
        items.filter { it.isBought }.size,
        items.size
    )
}