package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.models.domain.ShoppingList

interface BaseRepository {
    val currentLists: LiveData<List<ShoppingList>>
    val archivedLists: LiveData<List<ShoppingList>>

    suspend fun insertList(shoppingList: ShoppingList): Result<Long>
    suspend fun deleteList(listId: Long): Result<Int>
    suspend fun getListName(listId: Long): String
    suspend fun updateListName(listId: Long, newName: String): Result<Int>
    suspend fun setListIsArchived(listId: Long, archiveList: Boolean): Result<Boolean>
    fun getShoppingItems(listId: Long): LiveData<List<ShoppingItem>>
    suspend fun getItem(itemId: Long): ShoppingItem
    suspend fun insertItem(shoppingItem: ShoppingItem): Result<Long>
    suspend fun updateItem(shoppingItem: ShoppingItem): Result<Int>
    suspend fun reverseItemIsBought(itemId: Long): Result<Int>
}