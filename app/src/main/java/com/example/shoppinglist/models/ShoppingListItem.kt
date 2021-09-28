package com.example.shoppinglist.models

data class ShoppingListItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isBought: Boolean
)