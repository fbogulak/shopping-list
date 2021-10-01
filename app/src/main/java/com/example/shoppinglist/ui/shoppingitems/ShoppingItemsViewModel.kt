package com.example.shoppinglist.ui.shoppingitems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.R
import com.example.shoppinglist.repository.BaseRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand
import kotlinx.coroutines.launch

class ShoppingItemsViewModel(private val repository: BaseRepository) : BaseViewModel() {

    val listId = MutableLiveData(0L)
    var listIsArchived = false
    val shoppingItems = listId.switchMap { repository.getShoppingItems(it) }

    fun navToItemEdit(itemId: Long, listId: Long, destinationLabel: String) {
        navigationCommand.value = NavigationCommand.To(
            ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToItemEditFragment(
                itemId,
                listId,
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

    private fun navToShoppingLists() {
        navigationCommand.value =
            NavigationCommand.To(ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToMainFragment())
    }

    fun deleteShoppingList() {
        listId.value?.let {
            viewModelScope.launch {
                val result = repository.deleteList(it)
                result.onSuccess {
                    showToast(R.string.shopping_list_deleted)
                    navToShoppingLists()
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
                    navToShoppingLists()
                }
                result.onFailure {
                    showToast(R.string.error_archiving_unarchiving_list)
                }
            }
            return
        }
        showToast(R.string.error_archiving_unarchiving_list)
    }

    fun reverseItemIsBought(itemId: Long) {
        viewModelScope.launch {
            val result = repository.reverseItemIsBought(itemId)
            result.onFailure {
                showToast(R.string.error_changing_item_bought_status)
            }
        }
    }
}