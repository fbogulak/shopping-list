package com.example.shoppinglist.models.domain

data class ShoppingItem(
    val id: Long,
    var name: String,
    var quantity: Int,
    var isBought: Boolean
)