package com.example.shoppinglist.ui.shoppinglists.archivedlists

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.repository.ShoppingRepository
import java.util.*

class ArchivedListsViewModel(repository: ShoppingRepository) : ViewModel() {
    val shoppingLists = repository.archivedLists
}