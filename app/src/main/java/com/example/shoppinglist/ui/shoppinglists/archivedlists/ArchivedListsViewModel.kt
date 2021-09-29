package com.example.shoppinglist.ui.shoppinglists.archivedlists

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.ShoppingList
import com.example.shoppinglist.models.ShoppingListItem

class ArchivedListsViewModel : ViewModel() {
    val shoppingLists: List<ShoppingList> = listOf(
        ShoppingList(
            1,
            "List 1",
            mutableListOf(
                ShoppingListItem(1, "Grocery 1", 1, false),
                ShoppingListItem(2, "Grocery 2", 2, true),
                ShoppingListItem(3, "Grocery 3", 3, false),
                ShoppingListItem(4, "Grocery 4", 4, true),
            )
        ),
        ShoppingList(
            2,
            "List 2",
            mutableListOf(
                ShoppingListItem(11, "Grocery 11", 1, false),
                ShoppingListItem(22, "Grocery 22", 2, true),
                ShoppingListItem(33, "Grocery 33", 3, false),
                ShoppingListItem(44, "Grocery 44", 4, true),
            )
        )
    )
}