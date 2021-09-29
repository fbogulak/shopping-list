package com.example.shoppinglist.ui.shoppinglists.archivedlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.repository.ShoppingRepository

class ArchivedListsViewModelFactory(private val repository: ShoppingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArchivedListsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArchivedListsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}