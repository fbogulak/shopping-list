package com.example.shoppinglist.ui.listedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.R
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand
import kotlinx.coroutines.launch
import java.util.*

class ListEditViewModel(private val repository: ShoppingRepository) : BaseViewModel() {

    val shoppingList = ShoppingList(0, "", Calendar.getInstance().time, false)
    val listName = MutableLiveData("")

    private fun navToShoppingItems() {
        navigationCommand.value = NavigationCommand.To(
            ListEditFragmentDirections.actionListEditFragmentToShoppingItemsFragment(
                shoppingList.id, shoppingList.isArchived, shoppingList.name
            )
        )
    }

    fun saveShoppingList() {
        viewModelScope.launch {
            val result = if (shoppingList.id == 0L) {
                repository.insertList(shoppingList)
            } else {
                shoppingList.name = listName.value ?: ""
                repository.updateListName(shoppingList.id, shoppingList.name)
            }
            result.onSuccess {
                when (it) {
                    is Int -> {
                        showToast(R.string.shopping_list_updated)
                    }
                    is Long -> {
                        showToast(R.string.shopping_list_added)
                        shoppingList.id = it
                    }
                }
                navToShoppingItems()
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

    fun getListNameFromDb() {
        viewModelScope.launch {
            listName.value = repository.getListName(shoppingList.id)
        }
    }
}