package com.example.shoppinglist.ui.shoppingitems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.R
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand
import kotlinx.coroutines.launch

class ShoppingItemsViewModel(private val repository: ShoppingRepository) : BaseViewModel() {

    val listId = MutableLiveData(0L)
    var listIsArchived = false
    val shoppingItems = listId.switchMap { repository.getShoppingItems(it) }

    fun navToItemEdit(itemId: Long, destinationLabel: String) {
        navigationCommand.value = NavigationCommand.To(
            ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToItemEditFragment(
                itemId,
                destinationLabel
            )
        )
    }

    fun navToListEdit(destinationLabel: String) {
        listId.value?.let { listId ->
            navigationCommand.value = NavigationCommand.To(
                ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToListEditFragment(
                    listId,
                    destinationLabel
                )
            )
        }
    }

    private fun navigateBack() {
        navigationCommand.value = NavigationCommand.Back
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

    fun setListArchived(archiveList: Boolean) {
        listId.value?.let { listId ->
            viewModelScope.launch {
                val result = repository.setListIsArchived(listId, archiveList)
                result.onSuccess { wasArchiving ->
                    showToast(
                        if (wasArchiving) {
                            R.string.shopping_list_archived
                        } else {
                            R.string.shopping_list_unarchived
                        }
                    )
                    navigateBack()
                }
                result.onFailure {
                    showToast(R.string.error_archiving_unarchiving_list)
                }
            }
            return
        }
        showToast(R.string.error_archiving_unarchiving_list)
    }
}