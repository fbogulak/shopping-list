package com.example.shoppinglist.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.models.domain.ShoppingItem
import java.util.*

@Entity(tableName = "shopping_item_table")
data class DatabaseShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var quantity: Int,
    var timestamp: Long,
    var isBought: Boolean,
    val listId: Long
)

fun List<DatabaseShoppingItem>.asDomainModel(): List<ShoppingItem> {
    return map {
        ShoppingItem(
            it.id,
            it.name,
            it.quantity,
            Date(it.timestamp),
            it.isBought,
            it.listId
        )
    }
}

fun DatabaseShoppingItem.asDomainModel() = ShoppingItem(
    id,
    name,
    quantity,
    Date(timestamp),
    isBought,
    listId
)