package com.example.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.models.database.DatabaseShoppingItem

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingItem: DatabaseShoppingItem): Long

    @Update
    fun update(shoppingItem: DatabaseShoppingItem): Int

    @Delete
    fun delete(shoppingItem: DatabaseShoppingItem): Int

    @Query("SELECT * FROM shopping_item_table WHERE id = :id")
    fun getItemById(id: Long): DatabaseShoppingItem

    @Query("SELECT * FROM shopping_item_table WHERE listId = :listId ORDER BY timestamp ASC")
    fun getItemsByListId(listId: Long): LiveData<List<DatabaseShoppingItem>>

    @Query("DELETE FROM shopping_item_table WHERE listId = :listId")
    fun deleteItemsByListId(listId: Long): Int

    @Query("UPDATE shopping_item_table SET isBought = NOT isBought WHERE id = :id")
    fun reverseItemIsBoughtById(id: Long): Int
}