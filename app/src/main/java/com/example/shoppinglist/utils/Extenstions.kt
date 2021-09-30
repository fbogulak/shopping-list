package com.example.shoppinglist.utils

fun String?.toIntOrZero(): Int {
    return try {
        this?.toInt() ?: 0
    } catch (e: NumberFormatException) {
        0
    }
}