package com.example.shoppinglist.database

import androidx.room.*
import com.example.shoppinglist.models.database.DatabaseShoppingList

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
}