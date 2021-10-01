package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.models.domain.ShoppingList

class FakeTestRepository(
    var shoppingLists: MutableList<ShoppingList> = mutableListOf(),
    var shoppingItems: MutableList<ShoppingItem> = mutableListOf()
) : BaseRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun getShoppingItems(listId: Long): LiveData<List<ShoppingItem>> =
        liveData { shoppingItems.filter { it.listId == listId } }

    override suspend fun getItem(itemId: Long): ShoppingItem {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(shoppingItem: ShoppingItem): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(shoppingItem: ShoppingItem): Result<Int> {
        TODO("Not yet implemented")
    }

    override val currentLists: LiveData<List<ShoppingList>>
        get() = TODO("Not yet implemented")
    override val archivedLists: LiveData<List<ShoppingList>>
        get() = TODO("Not yet implemented")

    override suspend fun insertList(shoppingList: ShoppingList): Result<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteList(listId: Long): Result<Int> {
        if (shouldReturnError) {
            return Result.failure(Throwable("Test error"))
        }
        if (shoppingLists.remove(shoppingLists.find { it.id == listId })) {
            return Result.success(1)
        }
        return Result.failure(Throwable("List not found"))
    }

    override suspend fun getListName(listId: Long): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateListName(listId: Long, newName: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setListIsArchived(listId: Long, archiveList: Boolean): Result<Boolean> {
        if (shouldReturnError) {
            return Result.failure(Throwable("Test error"))
        }
        shoppingLists.find { it.id == listId }?.isArchived = archiveList
        if (shoppingLists.find { it.id == listId }?.isArchived == archiveList) {
            return Result.success(archiveList)
        }
        return Result.failure(Throwable("List not found"))
    }

    override suspend fun reverseItemIsBought(itemId: Long): Result<Int> {
        if (shouldReturnError) {
            return Result.failure(Throwable("Test error"))
        }
        val itemWasBought = shoppingItems.find { it.id == itemId }?.isBought
        shoppingItems.find { it.id == itemId }?.isBought = itemWasBought == false
        if (shoppingItems.find { it.id == itemId }?.isBought != itemWasBought) {
            return Result.success(1)
        }
        return Result.failure(Throwable("Item not found"))
    }
}