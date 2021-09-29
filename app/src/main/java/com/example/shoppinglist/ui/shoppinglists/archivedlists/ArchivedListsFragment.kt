package com.example.shoppinglist.ui.shoppinglists.archivedlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.FragmentArchivedListsBinding
import com.example.shoppinglist.ui.shoppinglists.adapters.ShoppingListsListAdapter

class ArchivedListsFragment : Fragment() {
    private val viewModel: ArchivedListsViewModel by viewModels()
    private lateinit var binding: FragmentArchivedListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArchivedListsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.archivedListsRecycler.adapter =
            ShoppingListsListAdapter(ShoppingListsListAdapter.ShoppingListListener { shoppingList ->
                Toast.makeText(requireContext(), "${shoppingList.name} clicked!", Toast.LENGTH_SHORT).show()
            })

        return binding.root
    }
}