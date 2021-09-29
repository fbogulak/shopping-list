package com.example.shoppinglist.models.domain

import com.example.shoppinglist.models.database.DatabaseShoppingList
import java.util.*

data class ShoppingList(
    val id: Long,
    var name: String,
    val date: Date,
    var isArchived: Boolean,
    val items: MutableList<ShoppingItem> = mutableListOf()
) {
    val progress = Pair(
        items.filter { it.isBought }.size,
        items.size
    )
}

fun ShoppingList.asDatabaseModel() = DatabaseShoppingList(
    id,
    name,
    date.time,
    isArchived
)