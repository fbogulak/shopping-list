package com.example.shoppinglist.ui.shoppingitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppinglist.databinding.FragmentShoppingItemsBinding
import com.example.shoppinglist.ui.shoppingitems.adapters.ShoppingItemsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingItemsFragment : Fragment() {

    private val viewModel: ShoppingItemsViewModel by viewModel()
    private lateinit var binding: FragmentShoppingItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)

        setupListId()
        setupRecycler()

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentShoppingItemsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupListId() {
        viewModel.listId.value = ShoppingItemsFragmentArgs.fromBundle(requireArguments()).argListId
    }

    private fun setupRecycler() {
        binding.shoppingItemsRecycler.adapter =
            ShoppingItemsListAdapter(ShoppingItemsListAdapter.ShoppingItemListener { shoppingItem ->
                Toast.makeText(requireContext(), "${shoppingItem.name} clicked", Toast.LENGTH_SHORT)
                    .show()
            })
    }
}