package com.example.shoppinglist.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.models.domain.ShoppingList
import java.util.*

@Entity(tableName = "shopping_list_table")
data class DatabaseShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    val timestamp: Long,
    var isArchived: Boolean
)

fun List<ShoppingListWithItems>.asDomainModel(): List<ShoppingList> {
    return map {
        ShoppingList(
            it.shoppingList.id,
            it.shoppingList.name,
            Date(it.shoppingList.timestamp),
            it.shoppingList.isArchived,
            it.items.asDomainModel() as MutableList<ShoppingItem>
        )
    }
}