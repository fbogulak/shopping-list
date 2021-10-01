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

    suspend fun deleteList(listId: Long): Result<Int> = withContext(Dispatchers.IO) {
        try {
            database.shoppingItemDao.deleteItemsByListId(listId)
            val deletedRows = database.shoppingListDao.deleteById(listId)
            return@withContext if (deletedRows == 1) {
                Result.success(deletedRows)
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    suspend fun getListName(listId: Long): String = withContext(Dispatchers.IO) {
        return@withContext database.shoppingListDao.getListNameById(listId)
    }

    suspend fun updateListName(listId: Long, newName: String): Result<Int> =
        withContext(Dispatchers.IO) {
            try {
                val updatedRows = database.shoppingListDao.updateListName(listId, newName)
                return@withContext if (updatedRows == 1) {
                    Result.success(updatedRows)
                } else {
                    Result.failure(Throwable())
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }

    suspend fun setListIsArchived(listId: Long, archiveList: Boolean): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val updatedRows =
                    database.shoppingListDao.setListIsArchivedById(listId, archiveList)
                return@withContext if (updatedRows == 1) {
                    Result.success(archiveList)
                } else {
                    Result.failure(Throwable())
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }

    fun getShoppingItems(listId: Long): LiveData<List<ShoppingItem>> =
        database.shoppingItemDao.getItemsByListId(listId).map { it.asDomainModel() }

    suspend fun getItem(itemId: Long): ShoppingItem = withContext(Dispatchers.IO) {
        return@withContext database.shoppingItemDao.getItemById(itemId).asDomainModel()
    }

    suspend fun insertItem(shoppingItem: ShoppingItem): Result<Long> = withContext(Dispatchers.IO) {
        try {
            val newId = database.shoppingItemDao.insert(shoppingItem.asDatabaseModel())
            return@withContext if (newId > 0L) {
                Result.success(newId)
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    suspend fun updateItem(shoppingItem: ShoppingItem): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val updatedRows = database.shoppingItemDao.update(shoppingItem.asDatabaseModel())
            return@withContext if (updatedRows == 1) {
                Result.success(updatedRows)
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    suspend fun reverseItemIsBought(itemId: Long): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val updatedRows = database.shoppingItemDao.reverseItemIsBoughtById(itemId)
            return@withContext if (updatedRows == 1) {
                Result.success(updatedRows)
            } else {
                Result.failure(Throwable())
            }
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

}