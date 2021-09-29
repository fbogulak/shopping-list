package com.example.shoppinglist.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class DatabaseShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    val timestamp: Long
)