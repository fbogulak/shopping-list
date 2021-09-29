package com.example.shoppinglist.ui.shoppinglists.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.models.domain.ShoppingList

@BindingAdapter("shoppingListsListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ShoppingList>?) {
    val adapter = recyclerView.adapter as ShoppingListsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("shoppingListProgress")
fun bindTextViewToProgress(textView: TextView, progress: Pair<Int, Int>?) {
    textView.text = textView.context.getString(
        R.string.shopping_list_progress_format,
        progress?.first,
        progress?.second
    )
}