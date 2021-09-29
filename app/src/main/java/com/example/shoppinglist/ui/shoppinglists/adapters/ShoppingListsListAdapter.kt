package com.example.shoppinglist.ui.shoppinglists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ShoppingListListItemBinding
import com.example.shoppinglist.models.domain.ShoppingList

class ShoppingListsListAdapter(private val clickListener: ShoppingListListener) :
    ListAdapter<ShoppingList, ShoppingListsListAdapter.ShoppingListViewHolder>(ShoppingListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingList = getItem(position)
        holder.bind(shoppingList, clickListener)
    }

    companion object ShoppingListDiffCallback : DiffUtil.ItemCallback<ShoppingList>() {
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem == newItem
        }
    }

    class ShoppingListViewHolder(private var binding: ShoppingListListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingList: ShoppingList, clickListener: ShoppingListListener) {
            binding.shoppingList = shoppingList
            binding.onClickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ShoppingListListItemBinding.inflate(layoutInflater, parent, false)
                return ShoppingListViewHolder(binding)
            }
        }
    }

    class ShoppingListListener(val clickListener: (shoppingList: ShoppingList) -> Unit) {
        fun onClick(shoppingList: ShoppingList) = clickListener(shoppingList)
    }
}