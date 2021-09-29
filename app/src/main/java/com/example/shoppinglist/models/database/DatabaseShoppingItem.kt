package com.example.shoppinglist.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item_table")
data class DatabaseShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var quantity: Int,
    var isBought: Boolean,
    val listId: Int
)