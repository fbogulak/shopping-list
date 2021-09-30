package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.models.database.asDomainModel
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.models.domain.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingRepository(private val database: ShoppingDatabase) {
    val currentLists by lazy {
        database.shoppingListDao.getCurrentListsWithItems().map { it.asDomainModel() }
    }
    val archivedLists by lazy {
        database.shoppingListDao.getArchivedListsWithItems().map { it.asDomainModel() }
    }

    suspend fun insertList(shoppingList: ShoppingList): Result<Long> = withContext(Dispatchers.IO) {
        try {
            val newId = database.shoppingListDao.insert(shoppingList.asDatabaseModel())
            return@withContext if (newId > 0L) {
                Result.success(newId)
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    fun getShoppingItems(listId: Long): LiveData<List<ShoppingItem>> =
        database.shoppingItemDao.getItemsByListId(listId).map { it.asDomainModel() }
}