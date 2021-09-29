package com.example.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shoppinglist.models.database.DatabaseShoppingItem
import com.example.shoppinglist.models.database.DatabaseShoppingList
import java.util.*
import java.util.concurrent.Executors

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
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    instance?.shoppingItemDao?.insert(
                                        DatabaseShoppingItem(
                                            1,
                                            "Tomatoes",
                                            4,
                                            false,
                                            1
                                        )
                                    )
                                    instance?.shoppingItemDao?.insert(
                                        DatabaseShoppingItem(
                                            2,
                                            "Bread",
                                            2,
                                            true,
                                            2
                                        )
                                    )
                                    instance?.shoppingListDao?.insert(
                                        DatabaseShoppingList(
                                            1,
                                            "Saturday Shopping",
                                            Calendar.getInstance().time.time,
                                            false
                                        )
                                    )
                                    instance?.shoppingListDao?.insert(
                                        DatabaseShoppingList(
                                            2,
                                            "Friday Shopping",
                                            Calendar.getInstance().time.time,
                                            true
                                        )
                                    )
                                }
                            }
                        })
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}