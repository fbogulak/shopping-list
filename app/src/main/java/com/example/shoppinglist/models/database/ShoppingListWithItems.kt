package com.example.shoppinglist.models.database

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingListWithItems(
    @Embedded val shoppingList: DatabaseShoppingList,
    @Relation(
        parentColumn = "id",
        entityColumn = "listId"
    )
    val items: List<DatabaseShoppingItem>
)
