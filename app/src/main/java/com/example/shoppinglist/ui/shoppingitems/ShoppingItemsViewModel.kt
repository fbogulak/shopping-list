package com.example.shoppinglist.ui.shoppingitems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.shoppinglist.repository.ShoppingRepository

class ShoppingItemsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    val listId = MutableLiveData(0L)
    val shoppingItems = listId.switchMap { repository.getShoppingItems(it) }
}