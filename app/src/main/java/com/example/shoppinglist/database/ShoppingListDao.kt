package com.example.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.models.database.DatabaseShoppingList
import com.example.shoppinglist.models.database.ShoppingListWithItems

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: DatabaseShoppingList): Long

    @Update
    fun update(shoppingList: DatabaseShoppingList): Int

    @Delete
    fun delete(shoppingList: DatabaseShoppingList): Int

    @Query("SELECT * FROM shopping_list_table WHERE id = :id")
    fun getShoppingList(id: Long): DatabaseShoppingList

    @Transaction
    @Query("SELECT * FROM shopping_list_table WHERE isArchived = 0 ORDER BY timestamp DESC")
    fun getCurrentListsWithItems(): LiveData<List<ShoppingListWithItems>>

    @Transaction
    @Query("SELECT * FROM shopping_list_table WHERE isArchived = 1 ORDER BY timestamp DESC")
    fun getArchivedListsWithItems(): LiveData<List<ShoppingListWithItems>>

    @Query("DELETE FROM shopping_list_table WHERE id = :id")
    fun deleteById(id: Long): Int

    @Query("SELECT name FROM shopping_list_table WHERE id = :id")
    fun getListNameById(id: Long): String

    @Query("UPDATE shopping_list_table SET name = :newName WHERE id = :id")
    fun updateListName(id: Long, newName: String): Int

    @Query("UPDATE shopping_list_table SET isArchived = :archive WHERE id = :id")
    fun setListIsArchivedById(id: Long, archive: Boolean): Int
}