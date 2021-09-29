package com.example.shoppinglist.ui.shoppinglists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.repository.ShoppingRepository

class ShoppingListsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    private val currentLists by lazy { repository.currentLists }
    private val archivedLists by lazy { repository.archivedLists }

    private var _shoppingLists: LiveData<List<ShoppingList>> = liveData { emptyList<ShoppingList>() }
    val shoppingLists: LiveData<List<ShoppingList>>
        get() = _shoppingLists

    fun showCurrentLists() {
        _shoppingLists = currentLists
    }

    fun showArchivedLists() {
        _shoppingLists = archivedLists
    }
}