package com.example.shoppinglist.ui.itemedit

import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand

class ItemEditViewModel(private val repository: ShoppingRepository) : BaseViewModel() {

    val shoppingItem = ShoppingItem(0, "", 1, false)

    private fun navigateBack() {
        navigationCommand.value = NavigationCommand.Back
    }

    fun saveShoppingItem() {
        navigateBack()
    }
}