package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.repository.BaseRepository
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.itemedit.ItemEditViewModel
import com.example.shoppinglist.ui.listedit.ListEditViewModel
import com.example.shoppinglist.ui.shoppingitems.ShoppingItemsViewModel
import com.example.shoppinglist.ui.shoppinglists.ShoppingListsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ShoppingListApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { ShoppingDatabase.getInstance(this@ShoppingListApp) }
            single { ShoppingRepository(get()) as BaseRepository }
            viewModel { ShoppingListsViewModel(get()) }
            viewModel { ListEditViewModel(get()) }
            viewModel { ShoppingItemsViewModel(get()) }
            viewModel { ItemEditViewModel(get()) }
        }

        startKoin {
            androidContext(this@ShoppingListApp)
            modules(appModule)
        }
    }
}