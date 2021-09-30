package com.example.shoppinglist.models.domain

import com.example.shoppinglist.models.database.DatabaseShoppingItem
import java.util.*

data class ShoppingItem(
    var id: Long,
    var name: String,
    var quantity: Int,
    var date: Date,
    var isBought: Boolean,
    var listId: Long
)

fun ShoppingItem.asDatabaseModel() = DatabaseShoppingItem(
    id,
    name,
    quantity,
    date.time,
    isBought,
    listId
)