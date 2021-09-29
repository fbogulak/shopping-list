package com.example.shoppinglist.models.domain

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isBought: Boolean
)