package com.example.shoppinglist.ui.listedit

import android.util.Log
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

    private fun navToShoppingItems() {
        navigationCommand.value = NavigationCommand.To(
            ListEditFragmentDirections.actionListEditFragmentToShoppingItemsFragment(
                shoppingList.id, shoppingList.name
            )
        )
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
                shoppingList.id = newId
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
}