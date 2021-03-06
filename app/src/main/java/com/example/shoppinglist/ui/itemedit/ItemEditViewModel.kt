package com.example.shoppinglist.ui.itemedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.R
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.repository.BaseRepository
import com.example.shoppinglist.ui.base.BaseViewModel
import com.example.shoppinglist.ui.base.NavigationCommand
import kotlinx.coroutines.launch
import java.util.*

class ItemEditViewModel(private val repository: BaseRepository) : BaseViewModel() {

    var shoppingItem = ShoppingItem(0, "", 0, Calendar.getInstance().time, false, 0)
    var itemName = MutableLiveData("")
    var itemQuantity = MutableLiveData<Int>()
    var itemDate = MutableLiveData<Date>()

    private fun navigateBack() {
        navigationCommand.value = NavigationCommand.Back
    }

    fun saveShoppingItem() {
        viewModelScope.launch {
            shoppingItem.name = itemName.value ?: ""
            shoppingItem.quantity = itemQuantity.value ?: 0
            shoppingItem.date = itemDate.value ?: Date()
            val result = if (shoppingItem.id == 0L) {
                repository.insertItem(shoppingItem)
            } else {
                repository.updateItem(shoppingItem)
            }
            result.onSuccess {
                when (it) {
                    is Int -> {
                        showToast(R.string.shopping_item_updated)
                    }
                    is Long -> {
                        showToast(R.string.shopping_item_added)
                        shoppingItem.id = it
                    }
                }
                navigateBack()
            }
            result.onFailure {
                val message = it.message
                if (message != null) {
                    showToast(message)
                } else {
                    showToast(R.string.error_saving_item)
                }
            }
        }
    }

    fun getItemFromDb() {
        viewModelScope.launch {
            shoppingItem = repository.getItem(shoppingItem.id)
            itemName.value = shoppingItem.name
            itemQuantity.value = shoppingItem.quantity
            itemDate.value = shoppingItem.date
        }
    }
}