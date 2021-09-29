package com.example.shoppinglist.models.domain

import java.util.*

data class ShoppingList(
    val id: Int,
    var name: String,
    val date: Date,
    val items: MutableList<ShoppingItem> = mutableListOf()
) {
    val progress = Pair(
        items.filter { it.isBought }.size,
        items.size
    )
}