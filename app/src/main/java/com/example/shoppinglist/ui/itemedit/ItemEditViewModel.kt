package com.example.shoppinglist.ui.itemedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.utils.ToastMessage

class ItemEditViewModel(private val repository: ShoppingRepository) : ViewModel() {

    val shoppingItem = ShoppingItem(0, "", 1, false)

    private val _navigateToShoppingItems = MutableLiveData<Boolean?>()
    val navigateToShoppingItems: LiveData<Boolean?>
        get() = _navigateToShoppingItems

    private val _showToast = MutableLiveData<ToastMessage<*>?>()
    val showToast: LiveData<ToastMessage<*>?>
        get() = _showToast

    fun navigateToShoppingItems() {
        _navigateToShoppingItems.value = true
    }

    fun navigateToShoppingItemsCompleted() {
        _navigateToShoppingItems.value = null
    }

    private fun showToast(message: String) {
        _showToast.value = ToastMessage.from(message)
    }

    private fun showToast(messageResId: Int) {
        _showToast.value = ToastMessage.from(messageResId)
    }

    fun showToastCompleted() {
        _showToast.value = null
    }

    fun saveShoppingItem() {
        navigateToShoppingItems()
    }
}