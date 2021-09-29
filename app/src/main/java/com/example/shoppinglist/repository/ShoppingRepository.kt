package com.example.shoppinglist.repository

import androidx.lifecycle.map
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.models.database.asDomainModel

class ShoppingRepository(private val database: ShoppingDatabase) {

    val currentLists by lazy {
        database.shoppingListDao.getCurrentListsWithItems().map { it.asDomainModel() }
    }
    val archivedLists by lazy {
        database.shoppingListDao.getArchivedListsWithItems().map { it.asDomainModel() }
    }
}