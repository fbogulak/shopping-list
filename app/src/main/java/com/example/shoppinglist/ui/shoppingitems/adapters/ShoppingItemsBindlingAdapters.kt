package com.example.shoppinglist.ui.shoppingitems.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.models.domain.ShoppingItem

@BindingAdapter("shoppingItemsListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ShoppingItem>?) {
    val adapter = recyclerView.adapter as ShoppingItemsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("shoppingItemQuantity")
fun bindTextViewToQuantity(textView: TextView, quantity: Int?) {
    textView.text = textView.context.getString(R.string.shopping_item_quantity_format, quantity)
}

@BindingAdapter("visibilityByIsArchived")
fun bindViewVisibilityToIsArchived(view: View, isArchived: Boolean?) {
    isArchived?.let {
        if (isArchived) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("itemIsBought")
fun bindImageViewToIsBought(imageView: ImageView, isBought: Boolean?) {
    isBought?.let { bought ->
        imageView.setImageResource(
            if (bought) R.drawable.ic_check_filled else R.drawable.ic_check_outlined
        )
    }
}