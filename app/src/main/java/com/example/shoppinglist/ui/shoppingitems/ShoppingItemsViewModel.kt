package com.example.shoppinglist.ui.shoppingitems

import androidx.lifecycle.*
import com.example.shoppinglist.R
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.utils.ToastMessage
import kotlinx.coroutines.launch

class ShoppingItemsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    val listId = MutableLiveData(0L)
    val shoppingItems = listId.switchMap { repository.getShoppingItems(it) }

    private val _navigateToItemEdit = MutableLiveData<Boolean?>()
    val navigateToItemEdit: LiveData<Boolean?>
        get() = _navigateToItemEdit

    private val _showToast = MutableLiveData<ToastMessage<*>?>()
    val showToast: LiveData<ToastMessage<*>?>
        get() = _showToast

    fun navigateToItemEdit() {
        _navigateToItemEdit.value = true
    }

    fun navigateToItemEditCompleted() {
        _navigateToItemEdit.value = null
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

    fun deleteShoppingList() {
        listId.value?.let {
            viewModelScope.launch {
                val result = repository.deleteList(it)
                result.onSuccess {
                    showToast(R.string.shopping_list_deleted)
                }
                result.onFailure {
                    val message = it.message
                    if (message != null) {
                        showToast(message)
                    } else {
                        showToast(R.string.error_deleting_list)
                    }
                }
            }
            return
        }
        showToast(R.string.error_deleting_list)
    }
}