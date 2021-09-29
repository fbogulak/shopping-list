package com.example.shoppinglist.ui.shoppinglists.currentlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.database.ShoppingDatabase
import com.example.shoppinglist.databinding.FragmentCurrentListsBinding
import com.example.shoppinglist.repository.ShoppingRepository
import com.example.shoppinglist.ui.shoppinglists.adapters.ShoppingListsListAdapter
import com.example.shoppinglist.ui.shoppinglists.archivedlists.ArchivedListsViewModel
import com.example.shoppinglist.ui.shoppinglists.archivedlists.ArchivedListsViewModelFactory

class CurrentListsFragment : Fragment() {
    private lateinit var viewModel: CurrentListsViewModel
    private lateinit var binding: FragmentCurrentListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val database = ShoppingDatabase.getInstance(requireContext())
        val repository = ShoppingRepository(database)
        viewModel =
            ViewModelProvider(
                this,
                CurrentListsViewModelFactory(repository)
            ).get(CurrentListsViewModel::class.java)

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