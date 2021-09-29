package com.example.shoppinglist.ui.shoppingitems.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ShoppingItemListItemBinding
import com.example.shoppinglist.models.domain.ShoppingItem

class ShoppingItemsListAdapter(private val clickListener: ShoppingItemListener) :
    ListAdapter<ShoppingItem, ShoppingItemsListAdapter.ShoppingItemViewHolder>(ShoppingItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val shoppingItem = getItem(position)
        holder.bind(shoppingItem, clickListener)
    }

    companion object ShoppingItemDiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }
    }

    class ShoppingItemViewHolder(private var binding: ShoppingItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingItem: ShoppingItem, clickListener: ShoppingItemListener) {
            binding.shoppingItem = shoppingItem
            binding.onClickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ShoppingItemListItemBinding.inflate(layoutInflater, parent, false)
                return ShoppingItemViewHolder(binding)
            }
        }
    }

    class ShoppingItemListener(val clickListener: (shoppingItem: ShoppingItem) -> Unit) {
        fun onClick(shoppingItem: ShoppingItem) = clickListener(shoppingItem)
    }
}