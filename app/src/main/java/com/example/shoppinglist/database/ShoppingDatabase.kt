package com.example.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.models.database.DatabaseShoppingItem
import com.example.shoppinglist.models.database.DatabaseShoppingList

@Database(
    entities = [DatabaseShoppingItem::class, DatabaseShoppingList::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract val shoppingItemDao: ShoppingItemDao
    abstract val shoppingListDao: ShoppingListDao

    companion object {

        @Volatile
        private var INSTANCE: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShoppingDatabase::class.java,
                        "shopping_database"
                    )
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}