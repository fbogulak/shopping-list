package com.example.shoppinglist.ui.shoppinglists.currentlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppinglist.databinding.FragmentCurrentListsBinding
import com.example.shoppinglist.ui.shoppinglists.adapters.ShoppingListsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentListsFragment : Fragment() {
    private val viewModel: CurrentListsViewModel by viewModel()
    private lateinit var binding: FragmentCurrentListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentListsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.currentListsRecycler.adapter =
            ShoppingListsListAdapter(ShoppingListsListAdapter.ShoppingListListener { shoppingList ->
                Toast.makeText(requireContext(), "${shoppingList.name} clicked!", Toast.LENGTH_SHORT).show()
            })

        return binding.root
    }
}