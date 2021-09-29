package com.example.shoppinglist.ui.shoppinglists.currentlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.repository.ShoppingRepository

class CurrentListsViewModelFactory(private val repository: ShoppingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentListsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrentListsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}