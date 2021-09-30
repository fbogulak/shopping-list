package com.example.shoppinglist.ui.shoppinglists

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand
import com.example.shoppinglist.ui.main.MainFragmentDirections

class ShoppingListsViewModel(private val repository: ShoppingRepository) : BaseViewModel() {

    private val currentLists by lazy { repository.currentLists }
    private val archivedLists by lazy { repository.archivedLists }

    private var _shoppingLists: LiveData<List<ShoppingList>> =
        liveData { emptyList<ShoppingList>() }
    val shoppingLists: LiveData<List<ShoppingList>>
        get() = _shoppingLists

    fun showCurrentLists() {
        _shoppingLists = currentLists
    }

    fun showArchivedLists() {
        _shoppingLists = archivedLists
    }

    fun navToListEdit(listId: Long, destinationLabel: String) {
        navigationCommand.value = NavigationCommand.To(
            MainFragmentDirections.actionMainFragmentToListEditFragment(
                listId,
                destinationLabel
            )
        )
    }

    fun navToShoppingItems(listId: Long, destinationLabel: String) {
        navigationCommand.value = NavigationCommand.To(
            MainFragmentDirections.actionMainFragmentToShoppingItemsFragment(
                listId, destinationLabel
            )
        )
    }
}