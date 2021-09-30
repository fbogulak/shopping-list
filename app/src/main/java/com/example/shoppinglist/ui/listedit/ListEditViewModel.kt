package com.example.shoppinglist.ui.listedit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.R
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.utils.ToastMessage
import kotlinx.coroutines.launch
import java.util.*

class ListEditViewModel(private val repository: ShoppingRepository) : ViewModel() {

    val shoppingList = ShoppingList(0, "", Calendar.getInstance().time, false)

    private val _navigateToShoppingItems = MutableLiveData<ShoppingList?>()
    val navigateToShoppingItems: LiveData<ShoppingList?>
        get() = _navigateToShoppingItems

    private val _showToast = MutableLiveData<ToastMessage<*>?>()
    val showToast: LiveData<ToastMessage<*>?>
        get() = _showToast

    fun navigateToShoppingItems(shoppingList: ShoppingList) {
        _navigateToShoppingItems.value = shoppingList
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
    
    fun saveShoppingList() {
        Log.v("ListEditViewModel", shoppingList.toString())
        viewModelScope.launch {
            val result = if (shoppingList.id == 0L) {
                repository.insertList(shoppingList)
            } else {
                repository.insertList(shoppingList)//TODO: update not insert!
            }
            result.onSuccess { newId ->
                showToast(R.string.shopping_list_added)
                navigateToShoppingItems(shoppingList.apply { id = newId })
            }
            result.onFailure {
                val message = it.message
                if (message != null) {
                    showToast(message)
                } else {
                    showToast(R.string.error_saving_list)
                }
            }
        }
    }
}